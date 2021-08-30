package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.as.domain.persistence.CurrencyType;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
}
