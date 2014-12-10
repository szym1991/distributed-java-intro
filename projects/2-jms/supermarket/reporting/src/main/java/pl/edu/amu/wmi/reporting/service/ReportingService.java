package pl.edu.amu.wmi.reporting.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import pl.edu.amu.wmi.common.Reporting;

/**
 *
 * @author Szymon
 */
public class ReportingService implements MessageListener {

    private final Reporting reporting = new Reporting();

    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage) message;
            try {
                Integer id = mapMessage.getInt("id");
                Double price = mapMessage.getDouble("price");
                reporting.updateReport(id, price);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }

}
