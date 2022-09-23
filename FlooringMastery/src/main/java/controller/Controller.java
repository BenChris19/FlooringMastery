package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DataPersistenceException;
import model.Order;
import model.Product;
import model.State;
import service.FloorService;
import service.InvalidDateException;
import service.InvalidOrderNumberException;
import service.OrderValidationException;
import view.FlooringView;

/**
 * Controller class for flooring mastery
 * @author benat
 *
 */
@Component
public class Controller {
	
	@Autowired
	private FlooringView view;
	private FloorService service;
	
	/**
	 * Constructor for controller
	 * @param service
	 * @param view
	 */
	public Controller(FloorService service, FlooringView view) {
		this.view = view;
		this.service = service;
	}
	
	/**
	 * Run the program
	 * @throws DataPersistenceException
	 * @throws InvalidOrderNumberException
	 * @throws OrderValidationException
	 * @throws IOException
	 * @throws InvalidDateException
	 */
	public void run() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, IOException, InvalidDateException {
		boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

		    menuSelection = getMenuSelection();

		    switch (menuSelection) {
		        case 1:
		            getOrders();
		            break;
		        case 2:
		            addOrder();
		            break;
		        case 3:
		            editOrder();
		            break;
		        case 4:
		            removeOrder();
		            break;
		        case 5:
		            keepGoing = false;
		            exitProgramme();
		            break;
		        default:
		            outOfBounds();
		    }
		}
    }

	/**
	 * Get the menu selection and prompt to the user
	 * @return
	 */
	private int getMenuSelection() {
		return view.displayMenu();
	}
	
	/**
	 * Exit the programme
	 */
	private void exitProgramme() {
		view.displayEndOfProgramme();
		
	}

	/**
	 * Get all of the orders
	 * @throws InvalidOrderNumberException
	 * @throws DataPersistenceException
	 * @throws InvalidDateException
	 */
	private void getOrders() throws InvalidOrderNumberException, DataPersistenceException, InvalidDateException {
		LocalDate date = view.displayGetOrders();
		view.displayOrdersOfDate(service.getOrders(date));
	}

	/**
	 * Add order to the file
	 * @throws DataPersistenceException
	 * @throws InvalidOrderNumberException
	 * @throws OrderValidationException
	 * @throws IOException
	 */
	private void addOrder() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, IOException {
		Order newOrder = view.displayAddOrder(this.service.getAllProducts(), this.service.getAllStates(), this.service.getLastOrderNumber());
		if(view.displayConfirmOrder().equals("Y")){
			this.service.addOrder(newOrder);
		}
	}

	/**
	 * Edit the order
	 * @throws DataPersistenceException
	 * @throws InvalidOrderNumberException
	 * @throws OrderValidationException
	 * @throws IOException
	 * @throws InvalidDateException
	 */
	private void editOrder() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, IOException, InvalidDateException {
		LocalDate editDate = view.displayEditOrderDate();
		int editOrderNum = view.displayEditOrderNum();
		this.service.editOrderExists(editDate, editOrderNum);
		List<Order> orderDate = this.service.getOrders(editDate);
		view.displayEditOrderFoundSuccess();
		
		String customerName = view.displayEditCustomerName(orderDate, editOrderNum);
		State state = view.displayEditCustomerState(orderDate, this.service.getAllStates(), editOrderNum);
		BigDecimal taxRate = state.getTaxRate();
		Product product = view.displayEditProductType(orderDate, this.service.getAllProducts(),editOrderNum);
		BigDecimal area = view.displayEditArea(orderDate, editOrderNum);
		
		
		Order t = new Order();
		for(Order o: orderDate) {
			if(o.getOrderNumber()==editOrderNum) {
				t=o;
			}
		}
		BigDecimal materialCost = area.multiply(t.getCostPerSquareFoot());
		BigDecimal laborCost = area.multiply(t.getLaborCostPerSquareFoot());
		BigDecimal tax = materialCost.add(laborCost).multiply(taxRate.divide(new BigDecimal("100")));
		BigDecimal total = materialCost.add(laborCost.add(tax));
		if(view.diplayConfirmEditOrder().equals("Y")){
			Order order = new Order();
	        order.setDate(t.getDate());
	        order.setOrderNumber(editOrderNum);
	        order.setCustomerName(customerName);
	        order.setState(state.getStateAbbreviation());
	        order.setTaxRate(state.getTaxRate());
	        order.setProductType(product.getProductType());
	        order.setArea(area);
	        order.setCostPerSquareFoot(t.getCostPerSquareFoot());
	        order.setLaborCostPerSquareFoot(t.getLaborCostPerSquareFoot());
	        order.setMaterialCost(materialCost);
	        order.setLaborCost(laborCost);
	        order.setTax(tax);
	        order.setTotal(total);
	        
	        this.service.editOrder(order);
		}
	}

	/**
	 * Remove order from file
	 * @throws DataPersistenceException
	 * @throws InvalidOrderNumberException
	 * @throws OrderValidationException
	 * @throws InvalidDateException
	 */
	private void removeOrder() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, InvalidDateException {
		LocalDate removeDate = view.displayRemoveOrderDate();
		int removeOrderNum = view.displayRemoveOrderNumber();
		Order removeOrder = null;
		this.service.editOrderExists(removeDate, removeOrderNum);
		List<Order> orderDate = this.service.getOrders(removeDate);
		for(Order o: orderDate) {
			if(o.getOrderNumber()==removeOrderNum) {
				removeOrder = o;
			}
		}
		view.displayEditOrderFoundSuccess();
		
		if(view.diplayConfirmRemoveOrder().equals("Y")) {
			this.service.removeOrder(removeOrder);
		}
		
	}

	/**
	 * Display out of bounds if user enters an option not in the menu
	 */
	private void outOfBounds() {
		view.displayInvalidOption();
		
	}

}
