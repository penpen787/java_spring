package lambdasinaction.chap8;

import org.junit.Test;

/**
 * @author kyunghun.jeon
 *         created on 2017. 2. 22..
 */
public class A_AnonymousClass {

	final int FI = 1;

	@Test
	public void this_test() {

		Runnable r1 = () -> {
			// 람다에서는 람다를 감싸는 클래스에 접근 가능
			System.out.println(this.FI);
		};


		Runnable r2 = new Runnable() {

			int runnableVar = 2;

			public void run() {
				// 익명클래스에서의 this 는 익명클래스 자신을 가리킴
				System.out.println(this.runnableVar);
			}
		};
	}

	@Test
	public void shadow_variable_test() {

		int a1 = 10;
		int a2 = 20;

//		final int a = 10;
		Runnable r1 = () -> {
			// 컴파일 에러
//			int a1 = 30;
			System.out.println(a1);
		};


		Runnable r2 = new Runnable() {

			int runnableVar = 2;

			public void run() {
				int a2 = 30;
			}
		};

	}

	public static void doSomething(Runnable r) {r.run();}
	public static void doSomething(Task t) {t.execute();}

	@Test
	public void 모호함() {

		// 일반적 사용
		doSomething(new Task() {
			@Override
			public void execute() {
				System.out.println("단거 !!");
			}
		});

		// 람다 사용시 모호함 발생
		// ide 가 캐치해줌
//		doSomething(() -> System.out.println("단거 !!!"));

		// 명시적 형변환 필요
		doSomething((Task)() -> System.out.println("단거 !!!"));
	}
}

interface Task {
	public void execute();
}