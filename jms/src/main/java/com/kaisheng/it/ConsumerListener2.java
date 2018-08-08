package com.kaisheng.it;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author guojiabang
 * @date 2018/8/7 0007
 */
public class ConsumerListener2 implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("----->" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
