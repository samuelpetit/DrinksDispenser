package com.lombardodier.model;

/**
 * A simple enum to define all coin values
 *
 * @author Samuel PETIT
 */
public enum CoinValue {
	/**
	 * A five cents coin
	 */
	FIVE_CENTS(0.05),
	/**
	 * A ten cents coin
	 */
	TEN_CENTS(0.1),
	/**
	 * A twenty cents coin
	 */
	TWENTY_CENTS(0.2),
	/**
	 * A fifty cents coin
	 */
	FIFTY_CENTS(0.5),
	/**
	 * A one franc coin
	 */
	ONE_FRANC(1.0),
	/**
	 * A two francs coin
	 */
	TWO_FRANCS(2.0),
	/**
	 * A five cents coin
	 */
	FIVE_FRANCS(5.0);

	public Double value;

	private CoinValue(final Double value) {
		this.value = value;
	}

	/**
	 * Get the value of a coin
	 * @return the value of a coin
	 */
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
