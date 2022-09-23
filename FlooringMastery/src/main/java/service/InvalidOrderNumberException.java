package service;

/**
 * Invalid order number exception
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class InvalidOrderNumberException extends Exception {

    InvalidOrderNumberException(String message) {
        super(message);
    }

    InvalidOrderNumberException(String message, Throwable cause) {
        super(message, cause);
    }

}
