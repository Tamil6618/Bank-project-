package Account_check.zealuosbank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ZealuosbankApplicationTests
{   
	@Mock
	private accountrepository repo;

	@InjectMocks
	private accountservice service;

	private accountentity account;

	@Mock
	private transactionrepositary trepo;

	@InjectMocks
	private transcationservice tservice;
	private transationentity transaction1;
	private transationentity transaction2;

	@BeforeEach
	public void setup()
	{
		account=new accountentity();
		account.setAccountNumber(3647346855667l);
		account.setAccountHoldername("tamilselvan");
		account.setAccountHoldecontactno(857654545876l);
		account.setAccountIfsccode("2365bbg");
		account.setAccountHolderplace("salem");

	}
    

	// @Test
	//  public void accountcreationtest() 
	// {
    //   when(repo.save(any(accountentity.class))).thenReturn(account);
	//   accountentity result=service.creation(account);

	  //assertNotNull(result);
	//    assertNotEquals(account, result);
	  //assertEquals(BigDecimal.value(780000), result.getAccountBalance());


	  
	//}

	@Test
	public void testGettransactionByoneuser()
	{
		List<transationentity>transaction=Arrays.asList(transaction1,transaction2);
		when(trepo.findAllByAccount(account)).thenReturn(transaction);
		List<transationentity>result=tservice.gettransactionbyoneuser(account);

		//assertNotNull(result);
		//assertEquals(5, result);
		//assertEquals(transaction, result);
		verify(trepo,times(1)).findAllByAccount(account);

		


	}


}
