package service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.core.annotation.Order;

import dao.DataPersistenceException;


public interface FloorService {
	
    List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException,
    DataPersistenceException;

    Order calculateOrder(Order o) throws DataPersistenceException,
    OrderValidationException, StateValidationException, ProductValidationException;

    Order getOrder(LocalDate dateChoice, int orderNumber) throws
    DataPersistenceException, InvalidOrderNumberException;

    Order addOrder(Order o) throws DataPersistenceException;

    Order compareOrders(Order savedOrder, Order editedOrder)
    throws DataPersistenceException, StateValidationException,
    ProductValidationException;

    Order editOrder(Order updatedOrder) throws DataPersistenceException,
    InvalidOrderNumberException;

    Order removeOrder(Order removedOrder) throws DataPersistenceException,
    InvalidOrderNumberException;

}
