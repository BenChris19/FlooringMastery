package service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.core.annotation.Order;

import dao.DataPersistenceException;

public class FloorServiceImpl implements FloorService{

	public List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException, DataPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order calculateOrder(Order o) throws DataPersistenceException, OrderValidationException,
			StateValidationException, ProductValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getOrder(LocalDate dateChoice, int orderNumber)
			throws DataPersistenceException, InvalidOrderNumberException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order addOrder(Order o) throws DataPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order compareOrders(Order savedOrder, Order editedOrder)
			throws DataPersistenceException, StateValidationException, ProductValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order editOrder(Order updatedOrder) throws DataPersistenceException, InvalidOrderNumberException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order removeOrder(Order removedOrder) throws DataPersistenceException, InvalidOrderNumberException {
		// TODO Auto-generated method stub
		return null;
	}

}
