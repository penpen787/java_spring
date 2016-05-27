package java8book.chap3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.junit.Test;

/**
 * 람다식 예제
 */
public class SimpleLambdaExpression {
	
	String[] strs = {"A","B","C"};
	
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

		Arrays.sort(strs, (first, second) -> first.length() - second.length());		
	}
	
	@Test
	public void test2() {
		
		/*
		 * 다른 코드에 전달하려는 액션을 수행하는 메서드가 이미 있을 경우, 메서드 참조 특수 문법사용
		 */
		
		// 1. 일반 람다식
		Arrays.sort(strs, (x,y) -> x.compareToIgnoreCase(y) );
		
		// 2. 메서드표현식 전달
		Arrays.sort(strs, String::compareToIgnoreCase);
		
	}
	
	

}
