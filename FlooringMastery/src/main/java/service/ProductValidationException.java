package service;

@SuppressWarnings("serial")
public class ProductValidationException extends Exception {

    ProductValidationException(String message) {
        super(message);
    }

    ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
