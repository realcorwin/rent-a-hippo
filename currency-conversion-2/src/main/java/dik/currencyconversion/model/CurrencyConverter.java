package dik.currencyconversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConverter {

	private Long id;

	private String currencyFrom;

	private String currencyTo;

	private BigDecimal conversionRate;

}
