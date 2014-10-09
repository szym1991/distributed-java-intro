package exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {

    public MyThread(String name) {
        super.setName(name);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
