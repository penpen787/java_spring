package java8book.chap2;

import org.junit.Ignore;
import org.junit.Test;

public class InheritanceAndConstructorTest {

	@Test
	@Ignore
	public void inheritanceAndConstructorTest() {

		C c = new C(); // A B C
		System.out.println();

		B bc = new C(); // A B C
		System.out.println(bc instanceof C); // true
		// bc.c // Compile error
		C bcc = (C) bc;
		System.out.println(bcc.c); // 0
		System.out.println();

		C cInt = new C(1); // A - B - C (int)
		System.out.println();

		C cIntInt = new C(1, 2); // A - B (int) - C (int, int)
		System.out.println();

	}

	@Test
	public void timeLimitLoopTest() {
		long startTime = System.currentTimeMillis();

		while (System.currentTimeMillis() - startTime < 3000) {
			// do something
			System.out.println("A");
		}

	}

}

class A {
	int a = 0;

	public A() {
		System.out.println("A");
	}

	public A(int a) {
		this.a = a;
		System.out.println("A (int)");
	}
}

class B extends A {
	int b = 0;

	public B() {
		System.out.println("B");
	}

	public B(int b) {
		super(b);
		this.b = b;
		System.out.println("B (int)");
	}
}

class C extends B {
	int c = 0;

	public C() {
		System.out.println("C");
	}

	public C(int c) {
		this.c = c;
		System.out.println("C (int)");
	}

	public C(int c, int b) {
		super(b);
		this.c = c;
		System.out.println("C (int, int)");
	}
}
