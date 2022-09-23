package model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Product class with all fields from product .txt file
 * @author benat
 *
 */
public class Product {
	private String productType;
	private BigDecimal costPerSquareFoot;
	private BigDecimal laborCostPerSquareFoot;

}
