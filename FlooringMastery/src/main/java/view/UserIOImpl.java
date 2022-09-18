package view;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class UserIOImpl implements UserIO {
	private final Scanner sc = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
		
	}

	public String formatCurrency(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public String readString(String prompt) {
		// TODO Auto-generated method stub
		return null;
	}

	public String readString(String prompt, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal readBigDecimal(String prompt, int scale) {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min) {
		// TODO Auto-generated method stub
		return null;
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
                date = LocalDate.parse(stringInput); 
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
