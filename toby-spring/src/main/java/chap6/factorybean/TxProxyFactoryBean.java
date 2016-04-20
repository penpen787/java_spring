package chap6.factorybean;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import chap6.transaction.TransactionHandler;

public class TxProxyFactoryBean implements FactoryBean<Object> {

	Object target;
	PlatformTransactionManager transactionManager;
	String pattern;
	Class<?> serviceInterface;

	@Override
	public Object getObject() throws Exception {
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setTransactionManager(transactionManager);
		txHandler.setPattern(pattern);
		return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { serviceInterface }, txHandler);
	}

	// Factory Bean 이 생성하는 오브젝트의 타입은 DI 받은 인터페이스 타입에 따라 달라짐, 
	// 따라서 다양한 타입의 프록시 오브젝트 생성에 재사용 할 수 있음
	@Override
	public Class<?> getObjectType() {
		return serviceInterface;
	}

	// 싱글톤 빈이 아니라는 뜻이 아니라, getObject() 가 매번 같은 오브젝트를 리턴하지 않는다는 뜻임
	@Override
	public boolean isSingleton() {
		return false;
	}
	
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
