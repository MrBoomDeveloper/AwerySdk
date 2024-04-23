package com.mrboomdev.awery.sdk.util.exceptions;

/**
 * Being thrown if no implementation was set to the object but was expected to have one.
 * @author MrBoomDev
 */
public class MissingImplementationException extends RuntimeException {

    /**
     * Creates a new instance of this class.
     * @author MrBoomDev
     */
    public MissingImplementationException() {
        super();
    }

    /**
     * Creates a new instance of this class.
     * @author MrBoomDev
     */
    public MissingImplementationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of this class.
     * @author MrBoomDev
     */
    public MissingImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of this class.
     * @author MrBoomDev
     */
    public MissingImplementationException(Throwable cause) {
        super(cause);
    }
}
