package Account_check.zealuosbank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/zealousbank")
@CrossOrigin(origins = "http://localhost:3000")
public class accountcontroller 

{ 
  @Autowired
  accountservice service;

  public PasswordEncoder encoder()
  {
    return new BCryptPasswordEncoder();
  }
   @GetMapping("/")
   public void run()
   {
    System.out.println("welcome to zealous bank regisert .....");
   }

   @GetMapping("/{user}")
   public accountentity purpose(@PathVariable("user")String user)
   {
    accountentity acconut1=(accountentity) service.loadUserByUsername(user);
    return acconut1;
   }

  @PostMapping("/accountcreate")
  public String accountcreate(@RequestBody accountentity accountdetails)
  {
    accountdetails.setPassword(encoder().encode(accountdetails.getPassword()));

    return service.creation(accountdetails).getAccountHoldername()+"has been created succesfully";
  }

  @GetMapping("/allaccount")
  public List<accountentity> alldetails() 
  {
    return service.allaccountholders();
  }
  @GetMapping("/findbyaccount/{accno}")
  public accountentity getaccdetail(@PathVariable("accno") long accno) 
  {
        return service.findbyaccont(accno);
  }

  @PutMapping("/updateaccountdetails")
  public String updateaccount(@RequestBody accountentity accountdetails) 
  {
        accountentity account1 = service.creation(accountdetails);

        return account1.getAccountNumber()+ " your account has been updated successfully";
  }
  @DeleteMapping("/deletebyid/{accno}")
  public String deleteaccount(@PathVariable("accno")long accno)
  {
    return service.deletebyaccountno(accno);
  }
  @GetMapping("/findbyplace/{place}")
  public List<accountentity>findbyplace(@PathVariable("place") String place)
  {
    return service.getallvalusesbyplace(place);

  }
  @GetMapping("/just")
  public void sample()
  {
    System.out.println("welcome to zealous bank");
  }
  


  @Autowired
  transcationservice tservice;

  @PostMapping("/createtransation")
  public transationentity transationcreate(@RequestBody transationentity transationdetails)
  {
    transationdetails.setCurrentBalance(transationdetails.getAccount().getAccountBalance());

    
    if (transationdetails.getTransactionType().equalsIgnoreCase("credit")) 
    {
      transationdetails.setCurrentBalance(transationdetails.getCurrentBalance().add(transationdetails.getTransactionAmount()));
       BigDecimal currentbalance1=transationdetails.getCurrentBalance();
       accountentity acc1=service.findbyaccont(transationdetails.getAccount().getAccountNumber());
       acc1.setAccountBalance(currentbalance1);
    }

    else if (transationdetails.getTransactionType().equalsIgnoreCase("debit"))
    
    {
      if (transationdetails.getAccount().getAccountBalance().compareTo(transationdetails.getTransactionAmount())>0) 
      {
       transationdetails.setCurrentBalance(transationdetails.getCurrentBalance().subtract(transationdetails.getTransactionAmount()));
       BigDecimal currentbalance1=transationdetails.getCurrentBalance();
       accountentity acc1=service.findbyaccont(transationdetails.getAccount().getAccountNumber());
       acc1.setAccountBalance(currentbalance1);
      }
      
    }
    

    return tservice.createtransaction(transationdetails);
  }
  @GetMapping("/getalltransation")
  public List<transationentity>getAlltransation()
  {
    return tservice.listalltransaction();
  }
  @GetMapping("/transactionbyid/{id}")
  public Optional<transationentity>gettransationbyid(@PathVariable Long id)
    {
      return tservice.findonetransaction(id);
    }
  @GetMapping("/gettransactionbyeoneaccount/{accno}")
  public List<transationentity>listdatas(@PathVariable("accno")Long accno)
  {
    accountentity account=service.findbyaccont(accno);
    return tservice.gettransactionbyoneuser(account);
  }
  @DeleteMapping("/deleteTransaction/{id}")
  public ResponseEntity<Void>deleteTransaction(@PathVariable Long id)
  {
   tservice.deletetrsnsaction(id);
   return ResponseEntity.noContent().build();
  }
}
