package pl.edu.amu.wmi.charityfleamarket;

import java.util.concurrent.TimeUnit;
import pl.edu.amu.wmi.charityfleamarket.roles.Donor;
import pl.edu.amu.wmi.charityfleamarket.roles.MarketManager;
import pl.edu.amu.wmi.charityfleamarket.roles.Recipient;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        
        MarketManager manager = MarketManager.getInstance();

        for(int i=1; i<=5; i++){
            manager.addParticipant(new Donor("Donor " + i));
        }

        for(int i=1; i<=10; i++){
            manager.addParticipant(new Recipient("Recipient " + i));
        }

        manager.start();

        //TO AVOID ENDLESS AUCTION
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
        
        }

        manager.close();
    }
}
