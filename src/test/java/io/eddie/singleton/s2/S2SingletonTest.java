package io.eddie.singleton.s2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class S2SingletonTest {

    @Test
    @DisplayName("테스트")
    void test() throws Exception {

        Thread thread1 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        });

        Thread thread2 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        });

        thread1.start();
        thread2.start();

    }

}
