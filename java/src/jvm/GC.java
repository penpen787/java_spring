package jvm;

import java.util.ArrayList;

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
		// System.gc();
		// someInstance.close();


		/**
		 * 컬렉션에 String 을 집어넣고 바로 제거
		 * list.get(i) 할 시, 얻어지는 값은 실제 값이 아닌 reference임
		 * obj = null 하면, reference 만 null 이 될 뿐, List 안에 실제 String 값이 없어지는것은 아님
		 * 이것이 Reachable but not Live
		 * Heap memory Leak 발생
		 */
		Leak lk = new Leak();
		for(int a=0; a<90000000; a++) {
			lk.addList(a);
			lk.removeStr(a);
		}
	}

	static class Leak {
		ArrayList list = new ArrayList();

		public void addList(int a) {
			list.add("가나다라" + a);
		}

		public void removeStr(int i) {
			Object obj = list.get(i);
			obj = null;
		}
	}

}
