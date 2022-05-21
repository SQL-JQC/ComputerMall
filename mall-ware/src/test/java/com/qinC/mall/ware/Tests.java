package com.qinC.mall.ware;

import com.qinC.mall.ware.config.MyRabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Tests {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

//    @Test
//    public void testSend() {
//        rabbitTemplate.convertAndSend(MyRabbitConfig.EXCHANGE_NAME, "qin.haha", "qinchuan mq hello~~~~~~");
//    }

}
