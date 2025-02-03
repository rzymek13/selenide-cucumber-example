package api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyRate {
    private String currency;
    private String code;
    private double midValue;

//    public CurrencyRate(String currency, String code, double midValue) {
//        this.currency = currency;
//        this.code = code;
//        this.midValue = midValue;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public double getMidValue() {
//        return midValue;
//    }
//
//    @Override
//    public String toString() {
//        return "CurrencyRate{" +
//                "currency='" + currency + '\'' +
//                ", code='" + code + '\'' +
//                ", mid=" + midValue +
//                '}';
//    }
}
