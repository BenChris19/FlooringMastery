package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private LocalDate date;
	private int orderNumber;
	private String customerName;
	private String state;
	private BigDecimal taxRate;
	private String productType;
	private BigDecimal area;
	private BigDecimal costPerSquareFoot;
	private BigDecimal laborCostPerSquareFoot;
	private BigDecimal materialCost;
	private BigDecimal laborCost;
	private BigDecimal tax;
	private BigDecimal total;
	
	public Order() {
		
	}

}
