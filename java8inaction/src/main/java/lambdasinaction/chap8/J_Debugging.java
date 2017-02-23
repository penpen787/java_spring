package lambdasinaction.chap8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author kyunghun.jeon
 *         created on 2017. 2. 22..
 */
public class J_Debugging {
	public static void main(String[] args) {
		List<I_Testing.Point> points = Arrays.asList(new I_Testing.Point(12, 2), null);
		points.stream().map(p -> p.getX()).forEach(System.out::println);
	}

	@Test
	public void 제로_나누기() {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		numbers.stream().map(J_Debugging::divideByZero).forEach(System.out::println);
	}

	public static int divideByZero(int n) {
		return n / 0;
	}
}
