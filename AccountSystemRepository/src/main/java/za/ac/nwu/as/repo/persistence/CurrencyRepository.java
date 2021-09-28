package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.Currency;

import java.math.BigDecimal;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Modifying
    @Query(value = "update currency c set c.currency_amount = :amount where currency_id = :id", nativeQuery = true)
    int updateCurrency(@Param("id") Integer currencyId, @Param("amount") BigDecimal amount);
}
