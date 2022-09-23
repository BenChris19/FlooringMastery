package dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.Order;
import service.OrderValidationException;

/**
 * Interface for order dao
 * @author benat
 *
 */
public interface OrderDao {

    List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException;
    
    int getLastOrderNumber() throws DataPersistenceException;

    Order addOrder(Order o) throws DataPersistenceException, OrderValidationException, IOException;

    Order editOrder(Order editedOrder) throws DataPersistenceException, OrderValidationException, IOException;

    Order removeOrder(Order o) throws DataPersistenceException, OrderValidationException;
}
