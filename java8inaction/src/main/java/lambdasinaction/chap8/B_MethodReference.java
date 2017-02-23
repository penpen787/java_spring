package lambdasinaction.chap8;

import lambdasinaction.chap3.Sorting.Apple;
import lambdasinaction.chap6.Dish;
import lambdasinaction.chap6.Grouping;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap6.Dish.menu;

/**
 * @author kyunghun.jeon
 *         created on 2017. 2. 22..
 */
public class B_MethodReference {

	private static Map<Grouping.CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
		return menu.stream().collect(
				groupingBy(dish -> {
					if (dish.getCalories() <= 400) return Grouping.CaloricLevel.DIET;
					else if (dish.getCalories() <= 700) return Grouping.CaloricLevel.NORMAL;
					else return Grouping.CaloricLevel.FAT;
				} ));
	}

	@Test
	public void simpleMethodReference() {
		// 위 코드를 다음과 같이 변경 가능
		Map<Grouping.CaloricLevel, List<Dish>> dishesByCaloricLevel =
				menu.stream().collect(groupingBy(Dish::getCaloricLevel));

		// 헬퍼 메서드 사용 - 비교 구현에 주의해야 함
		List<Apple > inventory = new ArrayList<>();
		inventory.sort(
				(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
		);
		// 코드 자체가 문제를 설명함
		inventory.sort(comparing(Apple::getWeight));

		// 내장 컬렉터를 사용하면 코드 자체를 더 명확하게 설명 가능
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
	}


	@Test
	public void steram() {
		List<String> dishNames = new ArrayList<>();
		for (Dish dish : menu) {
			if (dish.getCalories() > 300) {
				dishNames.add(dish.getName());
			}
		}

		// 아래와 같이 스트림으로 변경가능
		menu.parallelStream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.collect(toList());
	}
}
