package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.as.domain.persistence.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
