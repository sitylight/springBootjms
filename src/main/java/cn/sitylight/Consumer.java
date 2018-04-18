// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-04-18, derrick.liang, creation
// ============================================================================
package cn.sitylight;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author derrick.liang
 */
@Component
public class Consumer {

    @JmsListener(destination = "ptp", containerFactory = "jmsQueueListenerCF")
    public void receive(String msg) {
        System.out.println(System.currentTimeMillis());
        System.out.println("ptp receive: " + msg);
    }
}
