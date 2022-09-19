package service;

@SuppressWarnings("serial")
public class OrderValidationException extends Exception {

    OrderValidationException(String message) {
        super(message);
    }

    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}