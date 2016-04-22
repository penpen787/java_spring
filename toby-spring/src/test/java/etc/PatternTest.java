package etc;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class PatternTest {
	
	@Test
	public void patternTest() throws NoSuchMethodException, SecurityException {
		String pattern = "";
		Method method = PatternTest.class.getMethod("patternTest", null);
		System.out.println(method.toString());
		
		assertTrue(method.getName().startsWith(pattern));
		}

}
