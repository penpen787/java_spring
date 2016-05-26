package java8book.chap3;

import org.junit.Test;

public class FirstInterfaceTest {

	@Test
	public void test1() {
		// 1. 익명클래스 
		FirstInterface<String> fi1 = new FirstInterface<String>() {
			@Override
			public int method1(String t1) {
				return Integer.parseInt(t1);
			}
		};
		
		// 2. 람다표현식 - 기본표현
		FirstInterface<String> fi2 = (String t1) -> { return Integer.parseInt(t1); };

		// 2-1. 람다표현식 - 간결표현, 타입추론이 가능하기때문에 타입을 지정하지 않으며,
		// return 키워드를 () braket 으로 한줄 표현 가능
		FirstInterface<String> fi3 = t1 -> ( Integer.parseInt(t1) );
	}
}
