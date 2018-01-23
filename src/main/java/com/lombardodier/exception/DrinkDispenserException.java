package com.lombardodier.exception;

public class DrinkDispenserException extends RuntimeException {
    private static final long serialVersionUID = -8672136142129412233L;

	public DrinkDispenserException(String msg) {
		super(msg);
	}

	public DrinkDispenserException(String msg, Throwable t) {
		super(msg, t);
	}
}

