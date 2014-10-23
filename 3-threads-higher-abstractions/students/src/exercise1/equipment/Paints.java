package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paints {
    final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int available = 3;

    public void takePaint() throws InterruptedException {
        lock.lock();
        try {
            if (available == 0) {
                condition.await();
                //throw new IllegalStateException("There are no more paints!");
            }
            available -= 1;
        } finally {
            lock.unlock();
        }
    }

    public void returnPaint() {
        lock.lock();
        try {
            condition.signal();
            available += 1;
        } finally {
            lock.unlock();
        }
    }
}
