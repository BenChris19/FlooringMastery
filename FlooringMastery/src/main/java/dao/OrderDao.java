package dao;

import java.time.LocalDate;
import java.util.List;

import model.Order;

public interface OrderDao {

    List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException;

    Order addOrder(Order o) throws DataPersistenceException;

    Order editOrder(Order editedOrder) throws DataPersistenceException;

    Order removeOrder(Order o) throws DataPersistenceException;
}
