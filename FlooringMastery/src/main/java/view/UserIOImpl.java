package view;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserIOImpl implements UserIO {
	private final Scanner sc = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
		
	}

	public String formatCurrency(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public String readString() {	
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[a-zA-Z0-9\\s]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() && !stringInput.isBlank()) {
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

	public BigDecimal readBigDecimal() {
        boolean invalidInput = true;
        BigDecimal decimal = new BigDecimal("0.0");
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	decimal = new BigDecimal(stringValue);
            	invalidInput=false;
                
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return decimal;
	}

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

	public int readInt(String prompt, int min, int max) {
		// TODO Auto-generated method stub
		return 0;
	}

	public LocalDate readDate() {
        LocalDate date = null;
        boolean validInput = true;
        do {
            try {
                String stringInput= sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                date = LocalDate.parse(stringInput,formatter); 
                validInput = true;
            } catch (DateTimeException e) {
                this.print("Input error. Date is not in the correct format");
                validInput = false;
            }
        } while(!validInput);
        return date;
    }

	public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
		// TODO Auto-generated method stub
		return null;
	}

}
