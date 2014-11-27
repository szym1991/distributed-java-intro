package pl.edu.amu.wmi.charityfleamarket.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class MarketManager {
    
    private static MarketManager instance;
    private final ExecutorService executor;
    private final List<Runnable> roles;

    private MarketManager() {
        this.executor = Executors.newCachedThreadPool();
        this.roles = new ArrayList();
    }

    public static synchronized MarketManager getInstance() {
        if (instance == null) {
            instance = new MarketManager();
        }
        return instance;
    }

    public void addParticipant(Runnable participant) {
        roles.add(participant);
    }

    public void start() {
        roles.add(Chairman.getInstance());
        for(Runnable role : roles) {
            executor.execute(role);
        }
    }

    public void close() {
        executor.shutdownNow();
    }
}
