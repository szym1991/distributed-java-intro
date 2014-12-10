package pl.edu.amu.wmi.pointofsale.service;

import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import pl.edu.amu.wmi.common.Product;
import pl.edu.amu.wmi.pointofsale.PointOfSaleHolder;

/**
 *
 * @author Szymon
 */
public class ProductListService implements MessageListener {

    private Map<Integer, Product> products;
    private PointOfSaleHolder posHolder;

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public PointOfSaleHolder getPosHolder() {
        return posHolder;
    }

    public void setPosHolder(PointOfSaleHolder posHolder) {
        this.posHolder = posHolder;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage objectMessage = (ObjectMessage) message;
                Map<Integer, Product> productMap = (Map<Integer, Product>) objectMessage.getObject();
                getPosHolder().getPointOfSale().setProducts(productMap);
                products = productMap;
                System.out.println("New Product List:");
                for (Map.Entry<Integer, Product> productEntry
                        : posHolder.getPointOfSale().getProducts().entrySet()) {
                    System.out.println("id: " + productEntry.getValue().getId() 
                                + " name: " + productEntry.getValue().getName()
                                + " price: " + productEntry.getValue().getPrice());
                }
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }

}
