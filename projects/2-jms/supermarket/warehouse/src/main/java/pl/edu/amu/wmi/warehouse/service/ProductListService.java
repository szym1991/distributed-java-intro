package pl.edu.amu.wmi.warehouse.service;

import java.io.Serializable;
import java.util.Map;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import pl.edu.amu.wmi.common.Product;

/**
 *
 * @author Szymon
 */
public class ProductListService {

    private JmsTemplate jmsTemplate;

    private Destination productListTopic;
    private Destination productPriceTopic;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setProductListTopic(Destination productListTopic) {
        this.productListTopic = productListTopic;
    }

    public void setProductPriceTopic(Destination productPriceChangeTopic) {
        this.productPriceTopic = productPriceChangeTopic;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public Destination getProductListTopic() {
        return productListTopic;
    }

    public Destination getProductPriceTopic() {
        return productPriceTopic;
    }

    public void sendProductList(final Map<Integer, Product> productList) {
        getJmsTemplate().send(productListTopic, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage((Serializable) productList);
                return objectMessage;
            }
        });
    }

    public void changeProductPrice(final Product product) {
        getJmsTemplate().send(productListTopic, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setInt("id", product.getId());
                mapMessage.setDouble("price", product.getPrice());
                return mapMessage;
            }
        });

    }

}
