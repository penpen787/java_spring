package lambdasinaction.chap8;

import java.util.function.Consumer;

public class E_TemplateMethod {}

/**
 * 기존 방법
 */
abstract class OnlineBanking {
	/**
     * 각각의 은행 지점은 OnlineBanking 을 상속받아
     * makeCustomerHappy 를 구현함
     */
    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);


    // dummy Customer class
    static private class Customer {}
    // dummy Datbase class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }


}

/**
 * 람다 사용
 */
class OnlineBankingLambda {

	/**
     * makeCustomerHappy의 메서드 시그니처가 일치하도록 Consumer 파라미터 추가
     * @param id
     * @param makeCustomerHappy
     */
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    static private class Customer {}
    // dummy Database class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }

    public static void main(String[] args) {
        /**
        *  사용시 람다 표현식을 직접 전달        */
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
    }
}

