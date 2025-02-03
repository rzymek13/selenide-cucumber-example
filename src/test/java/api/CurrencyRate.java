package api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyRate {
    private String currency;
    private String code;
    private double midValue;

}
