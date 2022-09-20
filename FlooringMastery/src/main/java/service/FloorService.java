package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


import model.Order;
import model.Product;
import model.State;
import dao.DataPersistenceException;


public interface FloorService {
	
    List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException,
    DataPersistenceException;
    
    int getLastOrderNumber() throws DataPersistenceException;
    
    List<Product> getAllProducts() throws DataPersistenceException;
    
    List<State> getAllStates() throws DataPersistenceException;

    Order calculateOrder(Order o) throws DataPersistenceException,
    OrderValidationException, StateValidationException, ProductValidationException;

    Order getOrder(LocalDate dateChoice, int orderNumber) throws
    DataPersistenceException, InvalidOrderNumberException;

    Order addOrder(Order o) throws DataPersistenceException, OrderValidationException, IOException;

    Order compareOrders(Order savedOrder, Order editedOrder)
    throws DataPersistenceException, StateValidationException,
    ProductValidationException;

    void editOrderExists(LocalDate date, int orderNum) throws DataPersistenceException,
    InvalidOrderNumberException, OrderValidationException;
    
    Order editOrder(Order o) throws InvalidOrderNumberException, DataPersistenceException, OrderValidationException, IOException;

    Order removeOrder(Order removedOrder) throws DataPersistenceException,
    InvalidOrderNumberException, OrderValidationException;

}
