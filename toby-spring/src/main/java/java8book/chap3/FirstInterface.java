package java8book.chap3;

/**
 * 함수형 인퍼테이스는 단 하나의 추상 메서드만 있어야 한다. 
 */
@FunctionalInterface
public interface FirstInterface<T> {
	
	// 추상메서드
	int method1(T t1);
	
	/*
	 * Object 에 포함된 메서드는 해당하지 않는다.
	 * 참고 : http://stackoverflow.com/questions/6056124/do-interfaces-inherit-from-object-class-in-java
	 */
	boolean equals(Object obj);

	/*
	 * Default Method 도 해당하지 않는다.
	 */
	default boolean defMethod1(T t2) {
		return true;
	};
	
	/**
	 * Static method 도 해당하지 않는다.
	 */
	static String getName(String name) {
		return "Hello" + name;
	}
}
