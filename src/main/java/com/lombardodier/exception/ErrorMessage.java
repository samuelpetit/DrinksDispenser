package com.lombardodier.exception;

/**
 * A list of error messages
 * @author Samuel PETIT
 *
 */
public interface ErrorMessage {
	public static final String DRINK_NOT_EXISTING = "This drink doesn't exist";
	public static final String QUANTITY_NOT_AVAILABLE = "The quantity asked for this drink is not available";
	public static final String NO_POSSIBLE_REFUND = "No money left to return";
	public static final String NOT_ENOUGH_MONEY_INSERTED = "Please insert more money to buy this drink";
}
