package tddbook.chap1;

public class AccountTest {
	
	public static void main(String[] args) {
		AccountTest test = new AccountTest();
		try {
			test.testAccount();
		} catch (Exception e) {
			System.out.println("실패 ! " + e.getMessage());
			return;
		}
		System.out.println("성공!");
	}
	
	public void testAccount() throws Exception {
		Account account = new Account();
		if(account == null) 
			throw new Exception("계좌생성 실패");
	}
	
	

}
