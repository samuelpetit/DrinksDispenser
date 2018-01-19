package com.lombardodier.model;

import lombok.Builder;
import lombok.Data;

/**
 * A simple POJO class for drink object.
 *
 * @author Samuel PETIT
 */
@Data
@Builder
public class Drink {
	/**
	 * The identifier of the drink
	 */
	int id;
	
	/**
	 * The name of the drink
	 */
	String name;
	
	/**
	 * Quantity available for the drink
	 */
	int quantity;
	
	/**
	 * The unit price
	 */
	Double unitPrice;
	
	/**
	 * A description of the drink
	 */
	String description;
	
	/**
	 * The height of the drink (in centimeters)
	 */
	Double height;
	
	/**
	 * The weight of the drink (in pounds)
	 */
	Double weight;
	
	public Drink(DrinkBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.quantity = builder.quantity;
		this.unitPrice = builder.unitPrice;
		this.description = builder.description;
		this.height = builder.height;
		this.weight = builder.weight;
	}
	
	public static class DrinkBuilder {
		private final int id;
		private final String name;
		private final int quantity;
		private final Double unitPrice;
		private String description;
		private Double height;
		private Double weight;
		
		public DrinkBuilder(final int id, final String name, final int quantity, final Double unitPrice) {
			this.id = id;
			this.name = name;
			this.quantity = quantity;
			this.unitPrice = unitPrice;
		}
		
		public DrinkBuilder withDescription(final String description) {
			this.description = description;
			return this;
		}

		public DrinkBuilder withHeight(final Double height) {
			this.height = height;
			return this;
		}
		
		public DrinkBuilder withWeight(final Double weight) {
			this.weight = weight;
			return this;
		}
		
		public Drink build() {
			return new Drink(this);
		}
	}
}
