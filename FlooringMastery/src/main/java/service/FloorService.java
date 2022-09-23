package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


import model.Order;
import model.Product;
import model.State;
import dao.DataPersistenceException;

/**
 * Interface for service
 * @author benat
 *
 */
public interface FloorService {
	
    List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException,
    DataPersistenceException, InvalidDateException;
    
    int getLastOrderNumber() throws DataPersistenceException;
    
    List<Product> getAllProducts() throws DataPersistenceException;
    
    List<State> getAllStates() throws DataPersistenceException;

    Order addOrder(Order o) throws DataPersistenceException, OrderValidationException, IOException;

    void editOrderExists(LocalDate date, int orderNum) throws DataPersistenceException,
    InvalidOrderNumberException, OrderValidationException;
    
    Order editOrder(Order o) throws InvalidOrderNumberException, DataPersistenceException, OrderValidationException, IOException;

    Order removeOrder(Order removedOrder) throws DataPersistenceException,
    InvalidOrderNumberException, OrderValidationException;

}
