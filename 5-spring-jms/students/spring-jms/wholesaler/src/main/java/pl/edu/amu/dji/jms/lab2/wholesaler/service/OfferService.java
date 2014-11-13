package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab2.wholesaler.service.message.Offer;

@Service("offerService")
public class OfferService {

    @Autowired
    @Qualifier("offerJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("orderQueue")
    private Destination orderQueue;

    @Transactional
    public void sendOffer(final Double price) {
        Offer offer = new Offer(price, orderQueue);
        jmsTemplate.convertAndSend(offer);
    }
}
