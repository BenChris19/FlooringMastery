package controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DataPersistenceException;
import model.Order;
import service.FloorService;
import service.InvalidOrderNumberException;
import service.OrderValidationException;
import view.FlooringView;

@Component
public class Controller {
	
	@Autowired
	private FlooringView view;
	private FloorService service;
	
	public Controller(FloorService service, FlooringView view) {
		this.view = view;
		this.service = service;
	}
	
	public void run() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException {
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
		            break;
		        case 6:
		            keepGoing = false;
		            exitProgramme();
		            break;
		        default:
		            outOfBounds();
		    }
		}
		exitMessage();
    }

	private int getMenuSelection() {
		return view.displayMenu();
	}
	
	private void exitProgramme() {
		view.displayEndOfProgramme();
		
	}

	private void getOrders() throws InvalidOrderNumberException, DataPersistenceException {
		LocalDate date = view.displayGetOrders();
		view.displayOrdersOfDate(service.getOrders(date));
	}

	private void addOrder() throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException {
		Order newOrder = view.displayAddOrder(this.service.getAllProducts(), this.service.getAllStates(), this.service.getLastOrderNumber());
		this.service.addOrder(newOrder);
	}

	private void editOrder() {
		// TODO Auto-generated method stub
		
	}

	private void removeOrder() {
		// TODO Auto-generated method stub
		
	}

	private void outOfBounds() {
		view.displayInvalidOption();
		
	}

	private void exitMessage() {
		// TODO Auto-generated method stub
		
	}

}
