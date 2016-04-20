package etc;

import org.junit.Test;

/**
 * Reference Test
 * 
 *
 */
public class RefTest {

	public String pubStr = "PUBSTR";

	private String priStr = "PRISTR";

	public String getPriStr() {
		return priStr;
	}

	public void setPriStr(String priStr) {
		this.priStr = priStr;
	}

	@Test
	public void test() {
		
		RefTest refTest = new RefTest();
		doSomething(refTest);
		System.out.println(refTest.pubStr);		// pubMod
		System.out.println(refTest.getPriStr());// priMod

	}
	
	
	public void doSomething(RefTest test) {
		RefTest t = test;
		
		t.pubStr = "pubMod";
		t.setPriStr("priMod");
		
	}

}
