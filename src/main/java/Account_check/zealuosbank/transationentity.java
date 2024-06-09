package Account_check.zealuosbank;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactiondetils")
public class transationentity 
{
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "my_sequence_gens")
 @SequenceGenerator(name = "my_sequnce_gens" ,sequenceName = "my_sequences",allocationSize = 1)
 private Long transationNumber;
 private String transactionType;
 private BigDecimal currentBalance;
 private BigDecimal transactionAmount;
 private String transactionHoldernumber;
 @Column(name = "transacton_Date")
 private Date transactionDate;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="accountnumber",nullable = false)
 @JsonBackReference
 private accountentity account;


    
}
