package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class OrderService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage msg = (MapMessage) message;
        String retailerID;
        Double quantity;
        try {
            retailerID = msg.getString("retailerID");
            System.out.println("RetailerID: " + retailerID);
            quantity = msg.getDouble("quantity");
            System.out.println("Quantity: " + quantity);
        } catch (JMSException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
