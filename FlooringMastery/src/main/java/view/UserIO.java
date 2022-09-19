package view;

import java.math.BigDecimal;
import model.Product;
import model.State;

import java.time.LocalDate;
import java.util.List;

public interface UserIO {

    void print(String prompt);

    String formatCurrency(BigDecimal amount);

    String readString();

    BigDecimal readBigDecimal();
    
    BigDecimal readBigDecimal(String decimalOriginal);

    int readInt();

    LocalDate readDate();
    
    Product readProduct(List<Product> products, String productName);
    
    State readState(List<State> states, String stateNameAbr);
    
    String readCustomerName(String customerName);
    
    String readYesNo();
}
