package pl.edu.amu.wmi.charityfleamarket.roles;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class Donor implements Runnable {
    
    private final String name;
    private final Chairman chairman;
    
    public Donor(String name) {
        this.name = name;
        this.chairman = Chairman.getInstance();
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Item item = new Item(Items.getRandomName());
                while (!chairman.registerItem(item)) {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                }
                TimeUnit.SECONDS.sleep(new Random().nextInt(25) + 5);
            }
        } catch (InterruptedException e) {
            
        } finally {
            System.out.println(name + " says good bye.");
        }
    }

}
