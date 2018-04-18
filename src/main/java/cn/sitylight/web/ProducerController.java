// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-04-18, derrick.liang, creation
// ============================================================================
package cn.sitylight.web;

import cn.sitylight.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author derrick.liang
 */
@RestController
@RequestMapping(value = "/ptp")
public class ProducerController {
    @Autowired
    Producer producer;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Object convertAndSend() {
        producer.convertAndSend("derrick-test");
        System.out.println("send message " + "derrick-test");
        System.out.println(System.currentTimeMillis());
        return "success";
    }
}
