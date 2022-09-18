package view;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {

    void print(String prompt);

    String formatCurrency(BigDecimal amount);

    String readString(String prompt);

    String readString(String prompt, int max);

    BigDecimal readBigDecimal(String prompt, int scale);

    BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min);

    int readInt();

    LocalDate readDate(String prompt);

    LocalDate readDate(String prompt, LocalDate min, LocalDate max);

}
