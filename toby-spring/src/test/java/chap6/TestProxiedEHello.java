package chap6;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.lang.reflect.Proxy;

import org.junit.Test;

import chap6.pojo.Hello;
import chap6.pojo.HelloTarget;

public class TestProxiedEHello {
	
	@Test
	public void testProxy() {
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[] {Hello.class},
				new UppercaseHandler(new HelloTarget()));
		
		assertThat(proxiedHello.sayHello("toby"), is("HELLO TOBY"));
	}

}
