package com.lombardodier.model;

import java.io.Serializable;

import lombok.Data;

/**
 * A simple POJO class for drink object.
 *
 * @author Samuel PETIT
 */
@Data
public class Drink implements Serializable {
	private static final long serialVersionUID = 2519371577648434071L;

	/**
	 * The identifier of the drink
	 */
	private int id;
	
	/**
	 * The name of the drink
	 */
	private String name;
	
	/**
	 * Quantity available for the drink
	 */
	private int quantity;
	
	/**
	 * The unit price
	 */
	private Double unitPrice;
	
	/**
	 * A description of the drink
	 */
	private String description;
	
	/**
	 * The height of the drink (in centimeters)
	 */
	private Double height;
	
	/**
	 * The weight of the drink (in pounds)
	 */
	private Double weight;
	
	/**
	 * Constructor.
	 * @param builder a {@link DrinkBuilder} reprensetation of the drink
	 */
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

	/**
	 * Get the identifier of the drink
	 * @return id the identifier of the drink
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of the drink
	 * @return name the name of the drink
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the quantity remained for this drink
	 * @return quantity the quantity remained for the drink
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Get the quantity remained for this drink
	 */
	public void decreaseQuantity() {
		quantity --;
	}
	
	/**
	 * Get the unit price of the drink
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Get the description of the drink
	 * @return description the description of the drink
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the height of the drink
	 * @return height the height of the drink
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Get the weight of the drink
	 * @return weight the weight of the drink
	 */
	public Double getWeight() {
		return weight;
	}
}
