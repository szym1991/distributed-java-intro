package pl.edu.amu.wmi.pointofsale.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import pl.edu.amu.wmi.common.Product;
/**
 *
 * @author Szymon
 */
public class ProductSaleService {
    
    private JmsTemplate jmsTemplate;
    private Destination reportingTopic;
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Destination getReportingTopic() {
        return reportingTopic;
    }

    public void setReportingTopic(Destination reportingTopic) {
        this.reportingTopic = reportingTopic;
    }
    
    public void sellProduct(final Product product) {
        getJmsTemplate().send(reportingTopic, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage change = session.createMapMessage();
                change.setInt("id", product.getId());
                change.setDouble("price", product.getPrice());
                return change;
            }
        });

    }

    
}
