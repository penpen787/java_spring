package lambdasinaction.chap8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class H_FactoryMain {

    public static void main(String[] args) {
	    /**
         * 기존방법
         */
        Product p1 = ProductFactory.createProduct("loan");

	    /**
         * 람다 사용
         */
        // 생성자 직접 호출
        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();

        // 상품명을 생성자로 연결하는 Map 사용
        Product p3 = ProductFactory.createProductLambda("loan");

    }

	/**
     * Product Factory
     * name 을 입력받아 타입에 해당하는 new 인스턴스 반환
     */
    static private class ProductFactory {
        public static Product createProduct(String name){
            switch(name){
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
    }

    static private interface Product {}
    static private class Loan implements Product {}
    static private class Stock implements Product {}
    static private class Bond implements Product {}

    final static private Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
