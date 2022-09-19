package view;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {

    void print(String prompt);

    String formatCurrency(BigDecimal amount);

    String readString();

    BigDecimal readBigDecimal();

    int readInt();

    LocalDate readDate();

    LocalDate readDate(String prompt, LocalDate min, LocalDate max);

}
