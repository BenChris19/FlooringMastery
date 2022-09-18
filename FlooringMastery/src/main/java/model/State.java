package model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {
	private String stateAbbreviation;
	private String stateName;
	private BigDecimal taxRate;

}