package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Brushes {
    private String name;
    BlockingQueue queue = new ArrayBlockingQueue(10);
    
    public Brushes() {
        queue.offer("red");
        queue.offer("green");
        queue.offer("blue");
    }
    
    public String takeBrush() throws InterruptedException {
        return queue.take().toString();
    }

    public void returnBrush(String brush) {
        queue.offer(brush);
    }
}
