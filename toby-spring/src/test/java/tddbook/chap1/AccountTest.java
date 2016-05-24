package tddbook.chap1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	private Account account;

	@Before
	public void setup() {
		this.account = new Account(10000);
	}
	
	@Test
	public void testGetBalance() throws Exception {
		assertEquals(10000, account.getBalance());
	}
	
	@Test
	public void testDeoposit() {
		int balance = account.deposit(5000);
		assertEquals(15000, balance);
	}
	
	@Test
	public void testWithdraw() {
		int balance = account.withdraw(5000);
		assertEquals(5000, balance);
	}

}
