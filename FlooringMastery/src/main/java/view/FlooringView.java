package view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Order;
import model.Product;
import model.State;



@Component
public class FlooringView {
	
	@Autowired
	private UserIO io;
	
	public FlooringView(UserIO io) {
		this.io = io;
	}
	
	public int displayMenu() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*<<Flooring Program>>");
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Export All Data");
        io.print("*6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt();		
	}
	
	public void displayInvalidOption() {
		io.print("Please enter a number between 1 and 6!");
	}
	
	public void displayEndOfProgramme() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("* * * * * *END OF PROGRAMME!* * * * * *");
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	}
	
	public LocalDate displayGetOrders() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter a date in the format MMDDYYYY");
		return io.readDate();
	}
	
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
	
	public Order displayAddOrder(List<Product> products, List<State> states, int lastOrder) {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter a future date in the formate MMDDYYYY");
		LocalDate date = io.readDate();
		
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		io.print("Please enter Customer name");
		String name = io.readString();
		
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
		
		return order;
	}

}
