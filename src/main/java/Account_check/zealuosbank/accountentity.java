package Account_check.zealuosbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accountdetails")
public class accountentity implements UserDetails

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "my_sequence_gens")
    @SequenceGenerator(name = "my_sequence_gens",sequenceName = "my_sequences",allocationSize = 1)
    private Long accountNumber;
    private String accountHoldername;
    private String accountIfsccode;
    private BigDecimal accountBalance;
    private Long accountHoldecontactno;
    private String accountHolderplace;
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "connection",joinColumns = @JoinColumn(name="accountNumber"),inverseJoinColumns = @JoinColumn(name="transactionNumber"))
    private List<transationentity>transationlList=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return accountHoldername;
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password ;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    


    
    
}
