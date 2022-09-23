package service;

/**
 * Invalid date exception
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class InvalidDateException extends Exception {

	InvalidDateException(String message) {
        super(message);
    }

	InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

}
