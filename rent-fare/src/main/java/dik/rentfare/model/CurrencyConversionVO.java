package dik.rentfare.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CurrencyConversionVO {

    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;
}
