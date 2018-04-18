// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-04-18, derrick.liang, creation
// ============================================================================
package cn.sitylight;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author derrick.liang
 */
@EnableAutoConfiguration
@ComponentScan
@EnableJms
public class Application {
    @Bean
    public ConnectionFactory connectionFactory() {
        Map<String, Object> connectionParams = new HashMap<String, Object>();
        connectionParams.put(TransportConstants.PORT_PROP_NAME, 5445);
        connectionParams.put(TransportConstants.HOST_PROP_NAME, "localhost");
        TransportConfiguration transportConfiguration = new TransportConfiguration(
                NettyConnectorFactory.class.getName(), connectionParams);
        return HornetQJMSClient.createConnectionFactoryWithHA(JMSFactoryType.CF, transportConfiguration);
    }
    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    @Bean(name = "jmsQueueListenerCF")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-10");
        factory.setRecoveryInterval(1000L);
        return factory;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
