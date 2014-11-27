package pl.edu.amu.wmi.charityfleamarket.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class Recipient implements Runnable {
    
    private final Lock lock;
    private final Condition auctionFinished;
    private final String name;
    private final List<Item> items;
    private final Chairman chairman;
    private boolean winner = false;


    public Recipient(String name) {
        this.name = name;
        this.lock = new ReentrantLock();
        this.auctionFinished = lock.newCondition();
        this.items = new ArrayList<>();
        this.chairman = Chairman.getInstance();
    }

    public String getName() {
        return name;
    }

    public void addWonItem(Item item) {
        items.add(item);
        System.out.println(name + " won " + item.getName());
        winner = true;
    }

    public void notifyAuctionFinished() {
        lock.lock();
        try {
            auctionFinished.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                do {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } while (!chairman.getAccessForAuction(this));
                auctionFinished.await();
                if (winner) {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 5);
                    winner = false;
                }
            }
        } catch (InterruptedException e) {
            
        } finally {
            lock.unlock();
            if(items.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(name);
                sb.append(" says good bye leaving with items: ");
                for(Item item : items) {
                    sb.append(item.getName());
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                System.out.println(sb);
            }
            else
                System.out.println(name + " says good bye leaving with no items.");
        }

    }
}
