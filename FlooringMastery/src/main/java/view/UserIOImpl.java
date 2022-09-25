package view;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Product;
import model.State;

/**
 * Implements userio
 * @author benat
 *
 */
public class UserIOImpl implements UserIO {
	private final Scanner sc = new Scanner(System.in);

	/**
	 * Print message to user
	 */
	public void print(String msg) {
		System.out.println(msg);
		
	}

	/**
	 * Read string from user
	 */
	public String readString() {	
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[a-zA-Z0-9\\s]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() || stringInput.equals("")) {
            	string=stringInput;
            	validInput = true;
            }
            else {
            	this.print("Please only enter words");
            	validInput = false;
            }
        } while(!validInput);
        return string;
	}

	/**
	 * Read big decimal from user
	 */
	public BigDecimal readBigDecimal() {
        boolean invalidInput = true;
        BigDecimal decimal = new BigDecimal("0.0");
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	decimal = new BigDecimal(stringValue);
            	invalidInput=false;
            	if (new BigDecimal("100").compareTo(decimal) < 0) {
            		System.out.println("Please enter an area below 100 sq feet");
            		invalidInput=true;
            	}
                
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return decimal;
	}
	
	/**
	 * Read big decimal from user String
	 * @param decimalOriginal
	 */
	public BigDecimal readBigDecimal(String decimalOriginal) {
        boolean invalidInput = true;
        BigDecimal decimal = new BigDecimal("0.0");
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	if(stringValue.equals("")) {
            		decimal = new BigDecimal(decimalOriginal);
            		invalidInput=false;
            	}
            	else {
            		decimal = new BigDecimal(stringValue);
            		invalidInput=false;
            	}
                
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return decimal;
	}

	/**
	 * Read int from user
	 */
	public int readInt() {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = sc.nextLine();
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); 
                invalidInput = false; 
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
	}

	/**
	 * Read date from user. Prompt same question if entered date is not in the future (Today does not count)
	 */
	public LocalDate readDate() {
        LocalDate date = null;
        boolean validInput = true;
        do {
            try {
                String stringInput= sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                date = LocalDate.parse(stringInput,formatter); 
                if(LocalDate.now().isAfter(date)) {
                	System.out.println("Please enter a future date");
                	validInput = false;
                }
                else {
                	validInput = true;
                }
            } catch (DateTimeException e) {
                this.print("Input error. Date is not in the correct format");
                validInput = false;
            }
        } while(!validInput);
        return date;
    }
	
	/**
	 * Read product from user
	 * @param products
	 * @param productName
	 */
	public Product readProduct(List<Product> products,String productName) {
		Product foundProduct = null;
		for(Product p: products) {
			if(p.getProductType().equalsIgnoreCase(productName)) {
				foundProduct = p;
			}
		}
		return foundProduct;
	}

	/**
	 * Read state from user
	 * @param states
	 * @param stateNameAbr
	 */
	@Override
	public State readState(List<State> states, String stateNameAbr) {
		State foundState = null;
		for(State s: states) {
			if(s.getStateName().equalsIgnoreCase(stateNameAbr) || s.getStateAbbreviation().equalsIgnoreCase(stateNameAbr)) {
				foundState = s;
			}
		}
		return foundState;
	}

	/**
	 * Read customer name from user
	 * @param customerName
	 */
	@Override
	public String readCustomerName(String customerName) {
        String string = customerName;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[a-zA-Z0-9\\s,.]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() || stringInput.equals("")) {
            	string=stringInput;
            	validInput = true;
            }
            else {
            	this.print("Please only enter suitable characters");
            	validInput = false;
            }
        } while(!validInput);
        return string;
	}

	/**
	 * Read if the user has enter yes or no to confirm an order
	 */
	@Override
	public String readYesNo() {
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            if(stringInput.equals("Y") || stringInput.equals("N")) {
            	string=stringInput;
            	validInput = true;
            }
            else {
            	this.print("Please enter Y or N");
            	validInput = false;
            }
        } while(!validInput);
        return string;
	}

}
