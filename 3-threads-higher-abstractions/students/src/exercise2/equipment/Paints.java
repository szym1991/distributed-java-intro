package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Paints {
    BlockingQueue queue = new ArrayBlockingQueue(10);

    public Paints() {
        queue.offer("regular");
        queue.offer("triangular");
        queue.offer("spectacular");
    }
    public String takePaint() throws InterruptedException {
        return queue.take().toString();
    }

    public void returnPaint(String paint) {
        queue.offer(paint);
    }
}
