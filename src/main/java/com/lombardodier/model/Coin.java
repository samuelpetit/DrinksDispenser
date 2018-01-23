package com.lombardodier.model;

import java.io.Serializable;

import lombok.Data;

/**
 * A simple POJO class for coin object.
 *
 * @author Samuel PETIT
 */
@Data
public class Coin implements Serializable {
	private static final long serialVersionUID = -1820893890700407466L;

	/**
	 * The identifier of the coin
	 */
	private int id;
	
	/**
	 * The value of the coin
	 */
	private final Double value;
	
	/**
	 * The number of coin which are available
	 */
	private int availableQuantity;
	
	/**
	 * The height of the coin
	 */
	private Double height;
	
	/**
	 * The weight of the coin
	 */
	private Double weight;
	
	/**
	 * 
	 * Constructor.
	 * @param builder a {@link CoinBuilder} representation
	 */
	public Coin(final CoinBuilder builder) {
		this.id = builder.id;
		this.value = builder.value.getValue();
		this.availableQuantity = builder.availableQuantity;
		this.height = builder.height;
		this.weight = builder.weight;
	}
	
	public static class CoinBuilder {
		private final int id;
		private CoinValue value;
		private final int availableQuantity;
		private Double height;
		private Double weight;
		
		public CoinBuilder(final int id, final CoinValue value, final int availableQuantity) {
			this.id = id;
			this.value = value;
			this.availableQuantity = availableQuantity;
		}
		
		public CoinBuilder withHeight(final Double height) {
			this.height = height;
			return this;
		}
		
		public CoinBuilder withWeight(final Double weight) {
			this.weight = weight;
			return this;
		}
		
		public Coin build() {
			return new Coin(this);
		}
	}

	/**
	 * Get the id of the coin
	 * @return the id of the coin
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the value of a coin
	 * @return value the value of the coin
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Get the available quantity of coins contained in the drink dispenser
	 * @return availableQuantity the available quantity of coins contained in the drink dispenser
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * Set the available quantity of coins contained in the drink dispenser
	 * @param quantity the available quantity of coins contained in the drink dispenser
	 */
	public void setAvailableQuantity(final int quantity) {
		availableQuantity = quantity;
	}
	
	/**
	 * Add quantity to existing quantity for this coin in the drink dispenser
	 * @param quantity the quantity to add for this coin contained in the drink dispenser
	 */
	public void addQuantity(final int quantity) {
		availableQuantity += quantity;
	}
	
	/**
	 * Decrease quantity to existing quantity for this coin in the drink dispenser
	 * @param quantity the quantity to subtract for this coin contained in the drink dispenser
	 */
	public void decreaseQuantity(final int quantity) {
		availableQuantity -= quantity;
	}
	
	/**
	 * Get the coin's height
	 * @return height the coin's height
	 */
	public Double getHeight() {
		return height;
	}
	
	/**
	 * Get the coin's weight
	 * @return weight the coin's weight
	 */
	public Double getWeight() {
		return weight;
	}
}
