package dao;

/**
 * Throw data persistence exception
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class DataPersistenceException extends Exception {

    public DataPersistenceException(String message) {
        super(message);
    }

    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
