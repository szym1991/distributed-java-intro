package pl.edu.amu.wmi.charityfleamarket.roles;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class Chairman implements Runnable {
    private final Lock lock;
    private final Condition emptyQueue;
    private final Queue<Item> items;
    private final Queue<Recipient> recipients;
    private static Chairman instance;
    private boolean auctionStarted = false;

    private Chairman() {
        this.lock = new ReentrantLock();
        this.emptyQueue = lock.newCondition();
        this.items = new ArrayBlockingQueue<>(10);
        this.recipients = new ArrayBlockingQueue<>(10);
    }

    public static synchronized Chairman getInstance() {
        if (instance == null) {
            instance = new Chairman();
        }
        return instance;
    }

    public boolean registerItem(Item item) {
        lock.lock();
        boolean result = false;
        try {
            result = items.offer(item);
            if (result) {
                emptyQueue.signalAll();
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public boolean getAccessForAuction(Recipient recipient) {
        lock.lock();

        boolean result = false;
        try {
            result = auctionStarted && recipients.offer(recipient);
        } finally {
            lock.unlock();
        }
        if (result) {
            System.out.println("Registering " + recipient.getName());
        }
        return result;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Item item;
                try {
                    lock.lock();
                    while (items.isEmpty()) {
                        emptyQueue.await(5, TimeUnit.SECONDS);
                        if (items.isEmpty()) {
                            emptyItems();
                        }
                    }
                    item = items.poll();
                    auctionStarted = true;
                } finally {
                    lock.unlock();
                }
                TimeUnit.SECONDS.sleep(5);
                lock.lock();
                try {
                    auctionStarted = false;
                    Recipient winner = chooseWinner();
                    if (winner != null) {
                        System.out.println("Winner for auction " + item.getName() + " is " + winner.getName());
                        winner.addWonItem(item);
                    } else {
                        System.out.println("There is no winner for " + item.getName());
                    }
                    for(Recipient recipient : recipients) {
                        recipient.notifyAuctionFinished();
                    }
                    recipients.clear();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            
        } finally {
            System.out.println("Chairman says good bye.");
        }
    }
    
    private void emptyItems() {
        System.out.println("No auctions within 5 seconds. Closing the market.");
        MarketManager.getInstance().close();
    }

    private Recipient chooseWinner() {
        Recipient[] rs = recipients.toArray(new Recipient[0]);
        if (rs.length == 0) {
            return null;
        } else {
            return rs[new Random().nextInt(rs.length)];
        }
    }
}
