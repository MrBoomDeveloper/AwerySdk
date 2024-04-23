package com.mrboomdev.awery.sdk.util.exceptions;

/**
 * Being thrown if the permitted text has invalid syntax.
 * @author MrBoomDev
 */
public class InvalidSyntaxException extends RuntimeException {

	/**
	 * Constructor for InvalidSyntaxException.
	 * @author MrBoomDev
	 */
	public InvalidSyntaxException() {
		super();
	}

	/**
	 * Constructor for InvalidSyntaxException.
	 * @author MrBoomDev
	 */
	public InvalidSyntaxException(String message) {
		super(message);
	}

	/**
	 * Constructor for InvalidSyntaxException.
	 * @author MrBoomDev
	 */
	public InvalidSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for InvalidSyntaxException.
	 * @author MrBoomDev
	 */
	public InvalidSyntaxException(Throwable cause) {
		super(cause);
	}
}