package com.lombardodier.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.Getter;

import org.springframework.stereotype.Component;

import com.lombardodier.model.Coin.CoinBuilder;
import com.lombardodier.model.Drink.DrinkBuilder;

/**
 * A POJO to describe the content of the drink dispenser.
 * 
 * @author RDLB
 *
 */
@Data
@Component
public class DrinksDispenserContent {
	/**
	 * A list of {@link Drink}
	 */
	@Getter
	private List<Drink> drinks;
	/**
	 * A list of {@link Coin}
	 */
	@Getter
	private List<Coin> coins;

	/**
	 * Unique instance of drink dispenser
	 */
	private static DrinksDispenserContent instance = new DrinksDispenserContent();

	/**
	 * Default constructor
	 */
	private DrinksDispenserContent() {
		init();
	}

	/**
	 * Get the instance of drinks dispenser.
	 * 
	 * @return a representation of the drinks dispenser content
	 */
	public static DrinksDispenserContent getInstance() {
		return instance;
	}

	/**
	 * Method used to init the singleton
	 */
	public void init() {
		drinks = Arrays.asList(new DrinkBuilder(0, "7up", 10, 1.6).build(), //
		        new DrinkBuilder(1, "Coca", 10, 1.4).build(), //
		        new DrinkBuilder(2, "Fanta", 5, 1.1).build(), //
		        new DrinkBuilder(3, "Henniez", 15, 1.2).withHeight(15.0).build(), //
		        new DrinkBuilder(4, "Orangina", 20, 1.0).build(), //
		        new DrinkBuilder(5, "Pepsi", 10, 2.0).build(), //
		        new DrinkBuilder(6, "Perrier", 30, 0.8).withWeight(440.0).build(), //
		        new DrinkBuilder(7, "Schweppes", 5, 1.2).build());

		coins = Arrays.asList(new CoinBuilder(6, CoinValue.FIVE_FRANCS, 20).build(), //
		        new CoinBuilder(5, CoinValue.TWO_FRANCS, 20).build(), //
		        new CoinBuilder(4, CoinValue.ONE_FRANC, 20).build(), //
		        new CoinBuilder(3, CoinValue.FIFTY_CENTS, 20).build(), //
		        new CoinBuilder(2, CoinValue.TWENTY_CENTS, 20).build(), //
		        new CoinBuilder(1, CoinValue.TEN_CENTS, 20).build(), //
		        new CoinBuilder(0, CoinValue.FIVE_CENTS, 20).build());
	}

	/**
	 * Get all the drinks available in the dispenser
	 * 
	 * @return all the drinks available in the dispenser
	 */
	public List<Drink> getDrinks() {
		return drinks;
	}

	/**
	 * Get all the coins available in the dispenser
	 * 
	 * @return all the coins available in the dispenser
	 */
	public List<Coin> getCoins() {
		return coins;
	}
}
