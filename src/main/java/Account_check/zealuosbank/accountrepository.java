package Account_check.zealuosbank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountrepository extends JpaRepository<accountentity,Long>
{
 public List<accountentity>findByaccountHolderplace(String place);
 public accountentity findByaccountHoldername(String username);
    
} 
