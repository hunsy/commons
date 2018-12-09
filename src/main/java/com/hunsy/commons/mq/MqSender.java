package com.hunsy.commons.mq;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.hunsy.commons.utils.ApplicationContextUtils;
import org.springframework.stereotype.Component;

/**
 * mq发送消息
 * @author admin
 *
 */
@Slf4j
@Component
public class MqSender {

	
    /**
     * 发送消息
     * @param exchange exchange
     * @param routingKey routingKey
     * @param content 消息内容
     */
    public static void send(String exchange, String routingKey, String content) {
        
    	RabbitTemplate rabbitTemplate = ApplicationContextUtils.getBean(RabbitTemplate.class);
    	if(rabbitTemplate == null){
    		log.error("获取rabbitMQ的消息模板失败，请检查rabbitMQ的配置信息");
    	}
        rabbitTemplate.convertAndSend(exchange, routingKey, content);
    }
    
    /**
     * 延迟发送消息到队列
     * @param exchange exchange
     * @param routingKey routingKey
     * @param content 消息内容
     * @param time   why not a Date or long?
     */
    public static void send(String exchange, String routingKey, String content, final String time) {
    	RabbitTemplate rabbitTemplate = ApplicationContextUtils.getBean(RabbitTemplate.class);
    	if(rabbitTemplate == null){
    		log.error("获取rabbitMQ的消息模板失败，请检查rabbitMQ的配置信息");
    	}
    	log.info("开始发送延时mq...");
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(time);
                //持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchange, routingKey, content, processor);
    }


	
}
