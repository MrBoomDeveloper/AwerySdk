package com.mrboomdev.awery.sdk.util.exceptions;

/**
 * An exception that is thrown if the used component has not been initialized.
 * @author MrBoomDev
 */
public class DidNotInitException extends RuntimeException {

    /**
     * Creates an instance of {@link DidNotInitException}
     * @author MrBoomDev
     */
    public DidNotInitException() {
        super();
    }

    /**
     * Creates an instance of {@link DidNotInitException}
     * @author MrBoomDev
     */
    public DidNotInitException(String message) {
        super(message);
    }

    /**
     * Creates an instance of {@link DidNotInitException}
     * @author MrBoomDev
     */
    public DidNotInitException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an instance of {@link DidNotInitException}
     * @author MrBoomDev
     */
    public DidNotInitException(Throwable cause) {
        super(cause);
    }
}
