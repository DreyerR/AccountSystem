package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Modifying
    @Query(value = "insert into transaction (transaction_date, transaction_change, member_id) values" +
            " (:date, :change, :memberId)", nativeQuery = true)
    int saveTransaction(@Param("date")LocalDate date, @Param("change") String change, @Param("memberId") Integer memberId);
}
