package pl.edu.amu.wmi.pointofsale.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import pl.edu.amu.wmi.pointofsale.PointOfSaleHolder;

/**
 *
 * @author Szymon
 */
public class PriceService implements MessageListener {

    private PointOfSaleHolder posHolder;

    public PointOfSaleHolder getPosHolder() {
        return posHolder;
    }

    public void setPosHolder(PointOfSaleHolder posHolder) {
        this.posHolder = posHolder;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage) message;
            try {
                Double price = mapMessage.getDouble("price");
                Integer id = mapMessage.getInt("id");
                System.out.println("New price for product with ID = " + id + ": " + price);
                posHolder.getPointOfSale().changePrice(id, price);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("Wrong type of message");
        }
    }
}
