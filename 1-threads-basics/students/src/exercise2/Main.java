package exercise2;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new MyThread("Thread-" + (i + 1));
        }

        for (int i = 0; i < 4; i++) {
            threads[i].start();
        }
    }
}
