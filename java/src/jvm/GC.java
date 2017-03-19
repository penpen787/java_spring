package jvm;

/**
 * Java Performance Fundamental by 김한도
 * Created by PenPen on 2017. 3. 19..
 */
public class GC {

	public static void main(String... args) {

		/**
		 * 아래와 같은 메소드를 GC로 여기는 사람이 있다
		 * but 아래는 GC를 명시적으로 수행하거나 해당 객체의 사용을 중지하겠다는 의사표현일 뿐
		 * 실제 메모리에서 삭제하겠다는건 아님
 		 */
		System.gc();

	}

}
