package com.lombardodier.model;

import lombok.Builder;
import lombok.Data;

/**
 * A simple POJO class for coin object.
 *
 * @author Samuel PETIT
 */
@Data
@Builder
public class Coin {
	/**
	 * The identifier of the coin
	 */
	int id;
	
	/**
	 * The value of the coin
	 */
	Double value;
	
	/**
	 * The number of coin which are available
	 */
	int quantity;
	
	/**
	 * The height of the coin
	 */
	Double height;
	
	/**
	 * The weight of the coin
	 */
	Double weight;
	
	public Coin(final CoinBuilder builder) {
		this.id = builder.id;
		this.value = builder.value;
		this.quantity = builder.quantity;
		this.height = builder.height;
		this.weight = builder.weight;
	}
	
	public static class CoinBuilder {
		private final int id;
		private final Double value;
		private final int quantity;
		private Double height;
		private Double weight;
		
		public CoinBuilder(final int id, final Double value, final int quantity) {
			this.id = id;
			this.value = value;
			this.quantity = quantity;
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
}
