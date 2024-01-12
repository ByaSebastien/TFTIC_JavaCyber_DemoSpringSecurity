package be.tftic.spring.demo.bonus.lambdas;

import java.util.function.Consumer;

public class RefreshLambda {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("salut");

        consumer = (param) -> System.out.println(param);
        consumer.accept("salut2");
        consumer = System.out::println;
        consumer.accept("salut3");
    }

}
