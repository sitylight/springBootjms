// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-04-18, derrick.liang, creation
// ============================================================================
package cn.sitylight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.DeliveryMode;

/**
 * @author derrick.liang
 */
@Component
public class Producer {
    @Autowired
    JmsTemplate jmsTemplate;

    public void convertAndSend(String msg) {
        jmsTemplate.setDeliveryDelay(2000);
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.convertAndSend("ptp", msg);
    }

}
