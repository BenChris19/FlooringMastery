package view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Order;
import model.Product;
import model.State;


/**
 * View the user has for the flooring application
 * @author benat
 *
 */
@Component
public class FlooringView {
	
	@Autowired
	private UserIO io;
	
	/**
	 * Dependency injection
	 * @param io
	 */
	public FlooringView(UserIO io) {
		this.io = io;
	}
	
	/**
	 * Display the menu to the user
	 * @return
	 */
	public int displayMenu() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*<<Flooring Program>>");
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt();		
	}
	
	/**
	 * Display the user for invalid option
	 */
	public void displayInvalidOption() {
		io.print("Please enter a number between 1 and 6!");
	}
	
	/**
	 * Display the end of programme
	 */
	public void displayEndOfProgramme() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("* * * * * *END OF PROGRAMME!* * * * * *");
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	}
	
	/**
	 * Display the date the user has to enter to get orders from that date
	 * @return
	 */
	public LocalDate displayGetOrders() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter a date in the format MMDDYYYY");
		return io.readDate();
	}
	
	/**
	 * Display all orders for a particular date
	 * @param orders
	 */
	public void displayOrdersOfDate(List<Order> orders) {
		io.print("* * * * * * * * * * * * * * * * *Date:"+orders.get(0).getDate()+"* * * * * * * * * * * * * * * * * *");
		orders.forEach(o->{
			io.print("*Order number:"+o.getOrderNumber()+"\n"
					+ "*Customer:"+o.getCustomerName()+"\n"
					+ "*State:"+o.getState()+"\n"
					+ "*Tax rate:"+o.getTaxRate()+"\n"
					+ "*Product type:"+o.getProductType()+"\n"
					+ "*Area:"+o.getArea()+"\n"
					+ "*Cost per square foot:"+o.getCustomerName()+"\n"
					+ "*Labor cost per square foot:"+o.getCostPerSquareFoot()+"\n"
					+ "*Material cost:"+o.getMaterialCost()+"\n"
					+ "*Labor cost:"+o.getLaborCost()+"\n"
					+ "*Tax:"+o.getTax()+"\n"
					+ "-----TOTAL-----:"+o.getTotal()+"\n");
		});
	}
	
	/**
	 * Display add an order and prompt the user with options to enter to create the order
	 * @param products
	 * @param states
	 * @param lastOrder
	 * @return
	 */
	public Order displayAddOrder(List<Product> products, List<State> states, int lastOrder) {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter a future date in the formate MMDDYYYY");
		LocalDate date = io.readDate();
		
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter Customer name");
		String name = io.readCustomerName(null);
		
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter state name or state abbreviation");
		String stateNameAbr = io.readString();
		State state = io.readState(states, stateNameAbr);
		
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter desired product type");
		products.forEach(p->{io.print("---Product type: "+p.getProductType());
							 io.print("---Cost per square foot: "+p.getCostPerSquareFoot());
							 io.print("---Labor cost per square foot"+p.getLaborCostPerSquareFoot()+"\n");});
		String productTypeName = io.readString();
		Product productType = io.readProduct(products,productTypeName);
		
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter the area you want to buy in square foot (Just enter decimals)");
		BigDecimal area = io.readBigDecimal();
		
		BigDecimal costPerSquareFoot = productType.getCostPerSquareFoot();
		BigDecimal laborCostPerSquareFoot = productType.getLaborCostPerSquareFoot();
		BigDecimal materialCost = costPerSquareFoot.multiply(laborCostPerSquareFoot, new MathContext(4));
		BigDecimal laborCost = laborCostPerSquareFoot.multiply(area, new MathContext(4));
		BigDecimal tax = materialCost.add(laborCost).multiply(state.getTaxRate().divide(new BigDecimal("100")));
		BigDecimal total = materialCost.add(laborCost.add(tax));
		
		Order order = new Order();
        order.setDate(date);
        order.setOrderNumber(lastOrder);
        order.setCustomerName(name);
        order.setState(state.getStateAbbreviation());
        order.setTaxRate(state.getTaxRate());
        order.setProductType(productType.getProductType());
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        
        List<Order> temp = new ArrayList<>();
        temp.add(order);
        
        this.displayOrdersOfDate(temp);
		
		return order;
	}
	
	/**
	 * Display the user with order confirmation
	 * @return
	 */
	public String displayConfirmOrder() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Do you want to place order? (Y/N)");
		return io.readYesNo();
	}
	
	/**
	 * Display the user if they are sure they want to edit the order
	 * @return
	 */
	public String diplayConfirmEditOrder() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Are you sure you want to edit order? (Y/N)");
		return io.readYesNo();		
	}
	
	/**
	 * Display the user with the date they want to edit the order
	 * @return
	 */
	public LocalDate displayEditOrderDate() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter date of the order you want to edit");
		return io.readDate();
	}
	
	/**
	 * Display the user with the order number they want to edit
	 * @return
	 */
	public int displayEditOrderNum() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter order number");
		return io.readInt();
	}
	
	/**
	 * Display success if the order was found
	 */
	public void displayEditOrderFoundSuccess() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Order date and number found");		
	}
	
	/**
	 * Display edit customer name
	 * @param orders
	 * @param orderNum
	 * @return
	 */
	public String displayEditCustomerName(List<Order> orders, int orderNum) {
		String editName = null;
		for(Order o:orders) {
			if(o.getOrderNumber() == orderNum) {
				io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
				io.print("Enter customer name ("+ o.getCustomerName()+"):");
				editName = io.readCustomerName(o.getCustomerName());
			}
		}
		return editName;
	}
	
	/**
	 * Display edit customer state
	 * @param orders
	 * @param states
	 * @param orderNum
	 * @return
	 */
	public State displayEditCustomerState(List<Order> orders, List<State> states, int orderNum) {
		State editState = null;
		for(Order o:orders) {
			if(o.getOrderNumber() == orderNum) {
				io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
				io.print("Enter state name or abreviation ("+ o.getState()+"):");
				String nameAbr = io.readString();
				editState = io.readState(states, nameAbr);
			}		
		}
		if(editState==null) {
			State originalState = null;
			for(State state:states) {
				for(Order o:orders) {
					if(state.getStateAbbreviation().equalsIgnoreCase(o.getState())) {
						originalState = state;
					}
				}
			}
			return originalState;
		}
		else {
			return editState;
		}
	}

	/**
	 * Display customer with edit product type
	 * @param orders
	 * @param products
	 * @param orderNum
	 * @return
	 */
	public Product displayEditProductType(List<Order> orders,List<Product> products,int orderNum) {
		Product editProduct = null;
		for(Order o:orders) {
			if(o.getOrderNumber() == orderNum) {
				io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
				io.print("Choose one of the following product type");
				products.forEach(p->{io.print("---Product type: "+p.getProductType());
				 io.print("---Cost per square foot: "+p.getCostPerSquareFoot());
				 io.print("---Labor cost per square foot"+p.getLaborCostPerSquareFoot()+"\n");});
				io.print("Enter product type ("+ o.getProductType()+"):");
				String productType = io.readString();
				editProduct = io.readProduct(products, productType);
			}		
		}
		if(editProduct==null) {
			Product originalProduct = null;
			for(Product product:products) {
				for(Order o:orders) {
					if(product.getProductType().equalsIgnoreCase(o.getProductType())) {
						originalProduct = product;
					}
				}
			}
			return originalProduct;
		}
		else {
			return editProduct;
		}
	}

	/**
	 * Display edit area of floor.
	 * @param orders
	 * @param orderNum
	 * @return
	 */
	public BigDecimal displayEditArea(List<Order> orders, int orderNum) {
		BigDecimal editArea = null;
		for(Order o:orders) {
			if(o.getOrderNumber() == orderNum) {
				io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
				io.print("Enter area ("+ o.getArea()+"):");
				editArea = io.readBigDecimal(o.getArea().toString());
			}
		}
		return editArea;
	}
	
	/**
	 * Prompt the user with a date to enter to remove an order
	 * @return
	 */
	public LocalDate displayRemoveOrderDate() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter date you want to remove");
		return io.readDate();
	}
	
	/**
	 * Prompt the user with the order number to remove an order
	 * @return
	 */
	public int displayRemoveOrderNumber() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter order number you want to remove");
		return io.readInt();
	}
	
	/**
	 * Display to the user to confirm that the order has been removed
	 */
	public String diplayConfirmRemoveOrder() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Are you sure you want to remove order? (Y/N)");
		return io.readYesNo();		
	}
}
