package za.ac.nwu.as.domain.persistence;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @SequenceGenerator(name = "TRANSACTION_ID_SEQ", sequenceName = "TRANSACTION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_ID_SEQ")
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @Column(name = "TRANSACTION_CHANGE")
    private String transactionChange;

    @Column(name = "TRANSACTION_TOTAL")
    private BigDecimal transactionTotal;
}
