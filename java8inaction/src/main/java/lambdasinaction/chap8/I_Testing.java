package lambdasinaction.chap8;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class I_Testing {

    @Test
    public void 기존_테스트() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        Assert.assertEquals(15, p2.getX());
        Assert.assertEquals(5, p2.getY());

    }


	@Test
	public void 람다_테스트() {
		Point p1 = new Point(5, 5);
		Point p2 = new Point(10, 10);
		int result = Point.compareByXAndThenY.compare(p1, p2);
		Assert.assertEquals(-1, result);
	}


	/**
	 * 2 람다를 사용하는 메서드의 동작에 집중하라
 	 * 람다를 사용하는 메서드의 동작에 집중하라
	 */
	public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
		return points.stream()
				// 이부분의 테스트가 없다
				.map(p -> new Point(p.getX(), p.getX()))
				.collect(toList());
	}

	/**
	 * 위의 핵심 기능을 테스트
	 */
	@Test
	public void testMoveAllPointsRightBy() throws Exception {
		List<Point> points =
				Arrays.asList(new Point(5, 5), new Point(10, 5));
		List<Point> expectedPoints =
				Arrays.asList(new Point(15, 5), new Point(20, 5));
		List<Point> newPoints = I_Testing.moveAllPointsRightBy(points, 10);
		Assert.assertEquals(expectedPoints, newPoints);
	}

	/**
	 * 3 복잡한 람다를 개별메서드로 분할하기
	 * 복잡한 람다 표현식은 어떻게 테스트 할 것인가?
	 * 해결책 : 람다 표현식을 메서드 레퍼런스로 변경
	 */

	/**
	 * 4 고차원 함수 테스팅
	 * 고차원함수(함수를 인수로 받거나 다른 함수를 반환하는 메서드) 는 좀 더 사용하기 어려운데,
	 * 메서드가 람다를 인수로 받는다면 다른 람다로 메서드 동작 테스트 가능
	 */
	@Test
	public void testFilter() throws Exception {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		List<Integer> even = filter(numbers, i -> i % 2 == 0);
		Assert.assertEquals(Arrays.asList(2, 4), even);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for(T e: list) {
			if(p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}


    public static class Point{
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

	    public int getY() {
		    return y;
	    }

	    public void setY(int y) {
		    this.y = y;
	    }

        public Point moveRightBy(int x) {
            return new Point(this.x + x, this.y);
        }

	    /**
	     * 1 보이는 람다 표현식의 동작 테스팅
	     * 람다를 필드에 저장
	     */
	    public final static Comparator<Point> compareByXAndThenY =
			    comparing(Point::getX).thenComparing(Point::getY);

    }
}
