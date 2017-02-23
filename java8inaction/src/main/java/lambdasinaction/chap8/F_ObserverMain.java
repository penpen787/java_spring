package lambdasinaction.chap8;

import java.util.ArrayList;
import java.util.List;


public class F_ObserverMain {

    public static void main(String[] args) {
	    /**
         * 기존 방식
         */
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");


	    /**
         * 람다 사용
         */
        Feed feedLambda = new Feed();

	    /**
         * Observer 가 함수형 인터페이스므로 람다로 직접 전달 가능
         */
        feedLambda.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet); }
        });
        feedLambda.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet); }
        });

        feedLambda.notifyObservers("Money money money, give me money!");

    }


	/**
     * 옵서버 interface
     * 함수형 인터페이스임
     */
    @FunctionalInterface
    interface Observer{
        void inform(String tweet);
    }

	/**
     * 이벤트를 발생시키는 주체
     */
    interface Subject{
        void registerObserver(Observer o);
        void notifyObservers(String tweet);
    }

	/**
     * 각가의 언론사는 Observer를 구현함
     */
    static private class NYTimes implements Observer{
        @Override
        public void inform(String tweet) {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY!" + tweet);
            }
        }
    }

    static private class Guardian implements Observer{
        @Override
        public void inform(String tweet) {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        }
    }

    static private class LeMonde implements Observer{
        @Override
        public void inform(String tweet) {
            if(tweet != null && tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

	/**
     * 이벤트를 발생시키는 주체 구현
     */
    static private class Feed implements Subject{
        private final List<Observer> observers = new ArrayList<>();
        public void registerObserver(Observer o) {
            this.observers.add(o);
        }
        public void notifyObservers(String tweet) {
            observers.forEach(o -> o.inform(tweet));
        }
    }

}
