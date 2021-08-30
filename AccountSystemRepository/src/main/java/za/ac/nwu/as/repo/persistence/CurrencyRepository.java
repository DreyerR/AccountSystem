package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.as.domain.persistence.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
