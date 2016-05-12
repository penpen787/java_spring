package java8.chap1;

import org.junit.Test;

public class VariableTest {

	@Test
	public void test1() {
		
		int a = 1_200_000;
		int total;
		if(a != 0) {
			total = 0;
		} else {
			// Compile error
			// total++;
		}
		
	}
}
