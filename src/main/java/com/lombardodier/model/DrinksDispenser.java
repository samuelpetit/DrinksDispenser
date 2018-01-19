package com.lombardodier.model;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.lombardodier.model.Drink.DrinkBuilder;
import com.lombardodier.model.Coin.CoinBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class DrinksDispenser {
	List<Drink> drinks;
	List<Coin> coins;
	Double totalAmount;
	
	@PostConstruct
	  public void init(){
		drinks = Arrays.asList(
				new DrinkBuilder(1, "7up", 50, 1.6).build(),
				new DrinkBuilder(2, "coca", 50, 1.4).build(),
				new DrinkBuilder(3, "fanta", 50, 1.1).build(),
				new DrinkBuilder(4, "henniez", 50, 1.2).withHeight(15.0).build(),
				new DrinkBuilder(5, "orangina", 50, 1.0).build(),
				new DrinkBuilder(6, "pepsi", 50, 2.0).build(),
				new DrinkBuilder(7, "perrier", 50, 0.8).withWeight(440.0).build(),
				new DrinkBuilder(8, "schweppes", 50, 1.2).build()
				);
		
		coins = Arrays.asList(
				new CoinBuilder(1, 0.05, 20).build(),
				new CoinBuilder(2, 0.1, 20).build(),				
				new CoinBuilder(3, 0.2, 20).build(),				
				new CoinBuilder(4, 0.5, 20).build(),				
				new CoinBuilder(5, 1.0, 20).build(),								
				new CoinBuilder(6, 2.0, 20).build(),								
				new CoinBuilder(7, 5.0, 20).build()								
				);
		
		totalAmount = 50.0D;
	}
}
