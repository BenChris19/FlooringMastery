package service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DataPersistenceException;
import dao.OrderDao;
import dao.ProductDao;
import dao.StateDao;
import model.Order;

@Component
public class FloorServiceImpl implements FloorService{
	
	@Autowired
    private OrderDao orderDao;
    private ProductDao productDao;
    private StateDao stateDao;


    public FloorServiceImpl(OrderDao orderDao, ProductDao productDao, StateDao stateDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;
    }


    public List<Order> getOrders(LocalDate chosenDate) throws InvalidOrderNumberException,
            DataPersistenceException {
        List<Order> ordersByDate = orderDao.getOrders(chosenDate);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date.");
        }
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
