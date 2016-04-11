package chap6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import chap6.pojo.Hello;


public class UppercaseHandler implements InvocationHandler {
	
	Hello target;
	
	public UppercaseHandler(Hello hello) {
		this.target = hello;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String ret = (String)method.invoke(target, args);
		return ret.toUpperCase();	// 부가기능
	}

}
