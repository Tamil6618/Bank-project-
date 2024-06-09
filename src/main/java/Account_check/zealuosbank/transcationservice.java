package Account_check.zealuosbank;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transcationservice 
{

    @Autowired
    transactionrepositary trepo;

    public transationentity createtransaction(transationentity transationdetails)
    {
        return trepo.save(transationdetails);
    }
    public List<transationentity>listalltransaction()
    {
      return trepo.findAll();
    }
    public Optional<transationentity>findonetransaction(long transactionNumber)
    {
        return trepo.findById(transactionNumber);
    }
    public void deletetrsnsaction(Long id)
    {
        trepo.deleteById(id);
    }
    public List<transationentity>gettransactionbyoneuser(accountentity accountdetails)
    {
        return trepo.findAllByAccount(accountdetails);
    }

    

    
}
