package io.eddie.singleton.s1;

public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        System.out.println("Singleton.getInstance");

        if ( instance == null ) {
            System.out.println(Thread.currentThread().getName() + " - 인스턴스 생성!");
            instance = new Singleton();
        }

        return instance;
    }

}
