package dao;

import java.time.LocalDate;
import java.util.List;

import model.Order;
import service.OrderValidationException;

public interface OrderDao {

    List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException;
    
    int getLastOrderNumber() throws DataPersistenceException;

    Order addOrder(Order o) throws DataPersistenceException, OrderValidationException;

    Order editOrder(Order editedOrder) throws DataPersistenceException;

    Order removeOrder(Order o) throws DataPersistenceException;
}
