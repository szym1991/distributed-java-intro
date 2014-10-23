package exercise1;

import common.Counter;

public class SynchronizedCounter implements Counter {

    private long test =  0;
    
    @Override
    public synchronized void increment() {
        this.test++;
    }

    @Override
    public long getValue() {
        return this.test;
    }
}
