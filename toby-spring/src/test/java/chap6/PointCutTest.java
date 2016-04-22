package chap6;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import chap6.pojo.Hello;
import chap6.pojo.HelloTarget;

public class PointCutTest {

	@Test
	public void testPointCut() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("Penpen"), is("HELLO PENPEN"));
		// thankyou 는 sayH 와 매칭되지 않으므로 advice 동작 X
		assertThat(proxiedHello.sayThankYou("Penpen"), is("ThankYou Penpen"));

		
	}
}
