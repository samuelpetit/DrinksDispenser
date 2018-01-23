package com.lombardodier.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.lombardodier.exception.DrinkDispenserException;
import com.lombardodier.exception.ErrorMessage;
import com.lombardodier.model.Coin;
import com.lombardodier.model.Drink;
import com.lombardodier.model.DrinksDispenserContent;

/**
 * Controller for drinks dispenser management.
 * 
 * @author RDLB
 *
 */
@Controller
public class DrinksDispenserController {
	private static final String CONTENT = "content";
	private static final String RETURNED_COINS = "returnedCoins";

	private static final String GET_DRINK_PAGE = "searchForDrink";
	private static final String SUCCESS_PAGE = "success";

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private DrinksDispenserContent dispenserContent = DrinksDispenserContent.getInstance();

	/**
	 * Redirect to home page (to buy a drink).
	 * 
	 * @return a redirection URL
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/searchForDrink";
	}

	/**
	 * Load the customer form in order to by a drink.
	 * 
	 * @param model
	 *            a model
	 * @return the redirection URL of the form
	 */
	@RequestMapping(value = "/searchForDrink", method = RequestMethod.GET)
	public String loadBuyDrinkForm(Model model) {
		Gson gson = new Gson();
		String drinkDispenser = gson.toJson(dispenserContent);
		model.addAttribute(CONTENT, drinkDispenser);
		LOGGER.info("Content of drink dispenser : '{}'", drinkDispenser);
		return GET_DRINK_PAGE;
	}

	/**
	 * Service to buy a drink.
	 * 
	 * @param drinkChoice
	 *            the identifier of the {@link Drink} chosen by the customer
	 * @param coins
	 *            a list of inserted {@link Coin}
	 * @param insertedAmount
	 *            the total amount inserted by the customer
	 * @param model
	 *            a model
	 * @return a redirection URL
	 */
	@RequestMapping(value = "/buyDrink", method = RequestMethod.POST)
	public String buyDrink(@RequestParam("drinkChoice") int drinkChoice, @RequestParam("insertedCoins") Integer[] coins,
	        @RequestParam("insertedAmount") Double insertedAmount, Model model) {
		LOGGER.info("buyDrink ### Customer requested a drink");
		LOGGER.debug("buyDrink ### Inserted amount : {}", insertedAmount);
		List<Drink> availableDrinks = dispenserContent.getDrinks();
		List<Coin> availableCoins = dispenserContent.getCoins();

		Optional<Drink> wishedDrink = availableDrinks.stream().filter(drink -> drink.getId() == drinkChoice).findFirst();
		if (!wishedDrink.isPresent()) {
			LOGGER.error(ErrorMessage.DRINK_NOT_EXISTING);
			throw new DrinkDispenserException(ErrorMessage.DRINK_NOT_EXISTING);
		} else {
			Drink orderedDrink = wishedDrink.get();
			LOGGER.debug("buyDrink ### Requested drink : {}. Price : {}, Quantity remained : {}", orderedDrink.getName(), orderedDrink.getUnitPrice(),
			        orderedDrink.getQuantity());

			if (orderedDrink.getQuantity() == 0) {
				LOGGER.error(ErrorMessage.QUANTITY_NOT_AVAILABLE);
				throw new DrinkDispenserException(ErrorMessage.QUANTITY_NOT_AVAILABLE);
			}
			if (insertedAmount.compareTo(orderedDrink.getUnitPrice()) < 0) {
				LOGGER.error(ErrorMessage.NOT_ENOUGH_MONEY_INSERTED);
				throw new DrinkDispenserException(ErrorMessage.NOT_ENOUGH_MONEY_INSERTED);
			}

			double amountToReturn = BigDecimal.valueOf(insertedAmount).subtract(BigDecimal.valueOf(orderedDrink.getUnitPrice())).doubleValue();

			LOGGER.debug("buyDrink ### Amount to return to customer : {}", amountToReturn);
			if (Double.compare(amountToReturn, BigDecimal.ZERO.doubleValue()) > 0) {
				// We have to return money to customer
				Comparator<Coin> coinsValueComparator = (o1, o2) -> o1.getValue().compareTo(o2.getValue());
				availableCoins.sort(coinsValueComparator.reversed());

				// The map representing available coins in the drink dispenser
				TreeMap<Double, Integer> availableCoinsMap = new TreeMap<Double, Integer>(Collections.reverseOrder());
				availableCoinsMap.putAll(availableCoins.stream().collect(Collectors.toMap(Coin::getValue, Coin::getAvailableQuantity)));
				LOGGER.debug("buyDrink ### availables coins in drink dispenser : {}", availableCoinsMap);
				List<Double> availableCoinValues = availableCoins.stream().map(Coin::getValue).collect(Collectors.toList());

				// A map used to store coins to return
				Map<Double, Integer> coinsToReturnMap = availableCoinValues.stream().collect(HashMap::new, (map, v) -> map.put(v, new Integer(0)),
				        HashMap::putAll);

				LOGGER.info("calculateCoinsToReturn ### About to calculate coins to return to customer");
				calculateCoinsToReturn(amountToReturn, availableCoinsMap, coinsToReturnMap);
				LOGGER.info("calculateCoinsToReturn ### Finished to calculate coins to return");

				// We have to decrease available quantity for this drink, add
				// quantity of
				// coins inserted by the customer, and remove quantity of coins
				// returned to customer
				IntStream.range(0, coins.length).forEach(index -> availableCoins.get(index).addQuantity(coins[index]));
				coinsToReturnMap.forEach((index, value) -> availableCoins.stream().filter(coin -> coin.getValue() == index).findFirst().get()
				        .decreaseQuantity(value));
				orderedDrink.decreaseQuantity();

				LOGGER.debug("buyDrinks ### Quantity remained for this drink : {}", orderedDrink.getQuantity());
				LOGGER.info("buyDrinks ### Coins returned to customer : {}", coinsToReturnMap);
				Gson gson = new Gson();
				model.addAttribute(RETURNED_COINS, gson.toJson(coinsToReturnMap));
			}
		}

		return SUCCESS_PAGE;
	}

	/**
	 * Calculate the quantity of each coin we have to return to customer.
	 * 
	 * @param amountToReturn
	 *            the amount to return to customer
	 * @param availableCoinsMap
	 *            a {Map} representing a list of available coins in the drinks
	 *            dispenser (and its quantity)
	 * @param coinsToReturnMap
	 *            a {Map} representing a list of returned coins to customer (and
	 *            its quantity)
	 */
	public void calculateCoinsToReturn(double amountToReturn, Map<Double, Integer> availableCoinsMap, Map<Double, Integer> coinsToReturnMap) {
		// Check the sum of available coins in the drinks dispenser (check if we
		// can return money to customer)
		int availableAmountToReturn = availableCoinsMap.values().stream().mapToInt(Number::intValue).sum();
		if (availableAmountToReturn == 0) {
			LOGGER.error(ErrorMessage.NO_POSSIBLE_REFUND);
			throw new DrinkDispenserException(ErrorMessage.NO_POSSIBLE_REFUND);
		}

		// If we still have money to return to customer
		if (Double.compare(amountToReturn, BigDecimal.ZERO.doubleValue()) != 0) {
			// We get the next value of coin (ordered by descending value)
			Map.Entry<Double, Integer> availableCoinsEntry = availableCoinsMap.entrySet().iterator().next();
			// While the amount to return is greater than coin value, and the
			// remained quantity of this coin is positive, we continue
			while (Double.compare(amountToReturn, availableCoinsEntry.getKey()) >= 0 && availableCoinsEntry.getValue() > 0) {
				// Decrease the quantity of this coin in the drinks dispenser
				availableCoinsEntry.setValue(availableCoinsEntry.getValue() - 1);
				// Increase the quantity for this coin to return to customer
				coinsToReturnMap.computeIfPresent(availableCoinsEntry.getKey(), (k, v) -> v + 1);
				// Decrease the remaining amount to return with the coin value
				amountToReturn = BigDecimal.valueOf(amountToReturn).subtract(BigDecimal.valueOf(availableCoinsEntry.getKey())).doubleValue();
			}
			// If we still have amount to return to customer, we continue with
			// the lower coin value
			if (Double.compare(amountToReturn, BigDecimal.ZERO.doubleValue()) != 0) {
				availableCoinsMap.keySet().remove(availableCoinsEntry.getKey());
				calculateCoinsToReturn(amountToReturn, availableCoinsMap, coinsToReturnMap);
			}
		}
	}
}
