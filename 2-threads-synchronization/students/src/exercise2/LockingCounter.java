package exercise2;

import common.Counter;
import java.util.concurrent.locks.ReentrantLock;

public class LockingCounter implements Counter {

    private long test =  0;
    private final ReentrantLock lock = new ReentrantLock();
    
    @Override
    public void increment() {
        lock.lock();
        try {
            this.test++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return this.test;
    }
}
