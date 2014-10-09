package exercise3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        extension();
    }
    
    public static void basic() {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new MyRunnable(), "Thread-" + (i + 1));
        }

        for (int i = 0; i < 4; i++) {
            threads[i].run();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FINISHED");
    }
    
    public static void extension() {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new MyRunnable(), "Thread-" + (i + 1));
        }
        
        for (int i = 0; i < 4; i++) {
            threads[i].start();
        }
        
        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("FINISHED");
        
//        3.2 Continuous polling
//        boolean loop = true;
//        while(loop) {
//            for (int i = 0; i < 4; i++) {
//                if (!threads[i].isAlive())
//                    loop = false;
//            }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
}
