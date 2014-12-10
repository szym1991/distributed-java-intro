package pl.edu.amu.wmi.jmsserver;

import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Szymon
 */
public class JMSServer {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
