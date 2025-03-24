package io.eddie.singleton.s1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class S1SingletonTest {

    @Test
    @DisplayName("메모리 낭비를 해결한 싱글톤 테스트")
    void test() throws Exception {

        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();

    }

}
