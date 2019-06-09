package dik.currencyconversion.repository;

import dik.currencyconversion.model.CurrencyConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverter, Long> {

    CurrencyConverter findFirstByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
