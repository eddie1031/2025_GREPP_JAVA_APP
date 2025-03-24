package io.eddie.singleton.s2;

public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if ( instance == null ) {

            synchronized (Singleton.class) {
                if ( instance == null ) {
                    System.out.println(Thread.currentThread().getName() + " - 인스턴스 생성!");
                    instance = new Singleton();
                }
            }

        }
        return instance;

    }

}
