package pl.edu.amu.dji.jms.lab1;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloMain {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        /*
        Create Connection instance from ConnectionFactory

        Create Session instance from connection object.
        - session shouldn't by transactional and should by in auto acknowledge mode (Session.AUTO_ACKNOWLEDGE).

        Create Destination queue from session (check Session class and createQueue method)
        - queue name should be "SayHelloQueue"


        Create Destination topic instance from session (check Session class and createTopic method)
        - topic name should be "SayHelloTopic"
         */

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("SayHellloQueue");
        //Destination topic = session.createTopic("SayHellloQueue");
        //MessageConsumer consumer = session.createConsumer(queue);
        MessageConsumer consumer = session.createConsumer(queue, "dots = true");


        MessageListener helloListener;
        helloListener = new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getBooleanProperty("dots"));
                        System.out.println(textMessage.getText());
                    } catch (JMSException ex) {
                        Logger.getLogger(HelloMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        //Set MessageListener implementation as a message listener in MessageConsumer
        consumer.setMessageListener(helloListener);
        connection.start();
    }
}
