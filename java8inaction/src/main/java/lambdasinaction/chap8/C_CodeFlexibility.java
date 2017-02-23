package lambdasinaction.chap8;

import lambdasinaction.chap3.ExecuteAround;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kyunghun.jeon
 *         created on 2017. 2. 22..
 */
public class C_CodeFlexibility {

	@Test
	public void 조건부_연기_실행() {

		// 코드 내부에 제어 흐름문이 복잡하다
		Logger logger = null;
		if (logger.isLoggable(Level.FINER)) {
			logger.finer("Problem: " + doSomething());
		}

		// 위 코드의 문제 : isLoggable 메서드가 클라이언트 코드로 노출
		// 메시지 로깅시마다 logger 객체 상태 확인 필요

		// 일차적으로 아래와 같이 변경하는게 좋음
		// 이 코드는 logger 가 활성화 되어 있지 않더라도 로깅메세지를 평가하게 됨
		logger.log(Level.FINER, "Problem: " + doSomething());

		// 다음과 같이 변경 가능
		// java 1.8 에 추가된 log
		// 내부코드를 보면 Level 이 맞을 경우만 실행 됨
		logger.log(Level.FINER, () -> "Problem: " + doSomething());

	}



	@Test
	public void 실행_어라운드() throws Exception {
		// method we want to refactor to make more flexible
		String result = processFileLimited();
		System.out.println(result);

		System.out.println("---");

		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);

		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}

	public static String processFileLimited() throws IOException {
		try (BufferedReader br =
				     new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
			return br.readLine();
		}
	}

	// 인수로 전달된 p 실행
	public static String processFile(ExecuteAround.BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))){
			return p.process(br);
		}

	}

	// IO Exception 을 던실 수 있는 람다의 함수형 인터페이스
	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}

	public int doSomething() {return 1;}
}