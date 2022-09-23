package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DataPersistenceException;
import dao.OrderDao;
import dao.ProductDao;
import dao.StateDao;
import model.Order;
import model.Product;
import model.State;

/**
 * Implements floor service interface
 * @author benat
 *
 */
@Component
public class FloorServiceImpl implements FloorService{
	
	@Autowired
    private OrderDao orderDao;
    private ProductDao productDao;
    private StateDao stateDao;

    /**
     * Connect with dao layers
     * @param orderDao
     * @param productDao
     * @param stateDao
     */
    public FloorServiceImpl(OrderDao orderDao, ProductDao productDao, StateDao stateDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;
    }

    /**
     * Get orders
     * @param chosenDate
     */
    public List<Order> getOrders(LocalDate chosenDate) throws InvalidDateException, DataPersistenceException{
        List<Order> ordersByDate = orderDao.getOrders(chosenDate);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new InvalidDateException("ERROR: No orders "
                    + "exist on that date.");
        }
    }
    
    /**
     * Get last order date
     */
    public int getLastOrderNumber() throws DataPersistenceException {
        return orderDao.getLastOrderNumber();
    }    	
    
    /**
     * Get all products from text file
     */
    public List<Product> getAllProducts() throws DataPersistenceException{
    	List<Product> allProducts = this.productDao.getAllProducts();
        if (!allProducts.isEmpty()) {
            return allProducts;
        } else {
            throw new DataPersistenceException("ERROR: No orders "
                    + "exist on that date.");
        }
    }
    
    /**
     * Get all states from the text file
     */
    public List<State> getAllStates() throws DataPersistenceException{
    	List<State> allStates = this.stateDao.getAllStates();
        if (!allStates.isEmpty()) {
            return allStates;
        } else {
            throw new DataPersistenceException("ERROR: No orders "
                    + "exist on that date.");
        }
    }

    /**
     * Add order to the text file
     * @param o
     */
	public Order addOrder(Order o) throws DataPersistenceException, OrderValidationException, IOException {
		Order addOrder = null;
		if(o!=null) {
			addOrder = this.orderDao.addOrder(o);
		}
		else {
			throw new DataPersistenceException("Order is null.");			
		}
		return addOrder;
	}

	/**
	 * See if the order exists
	 * @param date
	 * @param orderNum
	 */
	public void editOrderExists(LocalDate date, int orderNum) throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException {
		Order editOrder = null;
		boolean found=false;
        List<Order> ordersByDate = orderDao.getOrders(date);
        if (!ordersByDate.isEmpty()) {
        	for(Order o:ordersByDate) {
        		if(o.getOrderNumber()==orderNum) {
        			found=true;
        			editOrder = o;
        		}
        	}

        }
        else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date or ther are no order numbers on the specified date.");
        }
	}

	/**
	 * Remove order from text file
	 * @param removeOrder
	 */
	public Order removeOrder(Order removedOrder) throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException {
		if(removedOrder!=null) {
			this.orderDao.removeOrder(removedOrder);
		}
		else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date or ther are no order numbers on the specified date.");			
		}
		return null;
	}

	/**
	 * Edit order from text file 
	 * @param o
	 */
	@Override
	public Order editOrder(Order o) throws InvalidOrderNumberException, DataPersistenceException, OrderValidationException, IOException {
		if(o!=null) {
			this.orderDao.editOrder(o);
		}
		else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date or ther are no order numbers on the specified date.");			
		}
		return null;
	}

}
