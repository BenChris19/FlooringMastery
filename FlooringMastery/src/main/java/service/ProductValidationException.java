package service;

/**
 * Validate the product
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class ProductValidationException extends Exception {

    ProductValidationException(String message) {
        super(message);
    }

    ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
