package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DataPersistenceException;
import service.FloorService;
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
	
	public void run() throws DataPersistenceException {
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
		            break;
		        default:
		            unknownCommand();
		    }

		}
		exitMessage();
    }

	private int getMenuSelection() {
		return view.displayMenu();
	}

	private void getOrders() {
		// TODO Auto-generated method stub
		
	}

	private void addOrder() {
		// TODO Auto-generated method stub
		
	}

	private void editOrder() {
		// TODO Auto-generated method stub
		
	}

	private void removeOrder() {
		// TODO Auto-generated method stub
		
	}

	private void unknownCommand() {
		// TODO Auto-generated method stub
		
	}

	private void exitMessage() {
		// TODO Auto-generated method stub
		
	}

}
