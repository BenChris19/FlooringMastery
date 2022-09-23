package model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * State class with all fields from state file
 * @author benat
 *
 */
public class State {
	private String stateAbbreviation;
	private String stateName;
	private BigDecimal taxRate;

}