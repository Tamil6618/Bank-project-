package Account_check.zealuosbank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionrepositary extends JpaRepository<transationentity ,Long >
{
    public List<transationentity>findAllByAccount(accountentity accountdetails);
 
    
} 
