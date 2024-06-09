package Account_check.zealuosbank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class accountservice implements UserDetailsService
{
    @Autowired              //one class to another class methode ah use panna object create pandrathuku pathila autowired use apndrom
    accountrepository repo; 

    public accountentity creation(accountentity accountdetails)
    {
        return repo.save(accountdetails);
        
    }
    
    
    public List<accountentity> allaccountholders() 
    {
        return repo.findAll();
    }

    public accountentity findbyaccont(long accountno)
    {
        
        return repo.findById(accountno).orElse(new accountentity());
    }

    public String deletebyaccountno(Long accountno)
    {
        accountentity accountdetail=repo.findById(accountno).orElse(new accountentity());
        repo.deleteById(accountno);
        return accountdetail.getAccountHoldername()+"has been deleted succesfuly";
    }
    public List<accountentity>getallvalusesbyplace(String place)
    {
      return repo.findByaccountHolderplace(place);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    { 
        accountentity acc=repo.findByaccountHoldername(username);
        if(acc == null)
        {
            throw new UsernameNotFoundException(username);

        }
      

        return acc;
    }
    
 }