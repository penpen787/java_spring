package java8book.chap3;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * 람다식 예제
 */
public class SimpleLambdaExpression {
	
	@Test
	public void test1() {
		
		// 파라미터를 받지 않을경우 빈괄호
		Runnable task = () -> {
			for (int i=0; i<1000; i++) System.out.println("A");
		};
		
		// 파라미터 타입을 추론할 수 있다면 타입 생략
		Comparator<String> comp = (first, second) -> first.length() - second.length();
		
		// 추론 대상의 타입 파라미터가 한개이면 괄호도 생략가능
		//EventHandler<ActionEvent> listener = event -> System.out.println("B");

		String[] strs = {"A","B","C"};
		Arrays.sort(strs, (first, second) -> first.length() - second.length());		
	}
	

}
