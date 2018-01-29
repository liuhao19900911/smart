package com.smart.control.kafka.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.smart.control.device.service.DeviceDataService;
import com.smart.control.kafka.service.KafkaConsumerServer;

/**
 * kafka监听器启动
 * 自动监听是否有消息需要消费
 * @author LH
 *
 */
public class KafkaConsumerServerImpl implements KafkaConsumerServer,MessageListener<String, String> {
	
    protected final Logger LOG = LoggerFactory.getLogger(KafkaConsumerServerImpl.class);
    
    @Autowired
    private DeviceDataService deviceDataService;
    
    /**
     * 监听器自动执行该方法
     *     消费消息
     *     自动提交offset
     *     执行业务代码
     *     （high level api 不提供offset管理，不能指定offset进行消费）
     */
    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        handleMessage(record.topic(), record.value());
    }
    
	@Override
	public void handleMessage(String topic, String message) {
		if (topic.equals("fromPlat11")) {
            deviceDataService.fromPlat11(message);
        } else if (topic.equals("fromPlat12")) {
            deviceDataService.fromPlat12(message);
        } else if (topic.equals("fromPlat13")) {
            deviceDataService.fromPlat13(message);
        } else if (topic.equals("fromPlat14")) {
            deviceDataService.fromPlat14(message);
        } else if (topic.equals("fromPlat15")) {
            deviceDataService.fromPlat15(message);
        } else if (topic.equals("fromPlat16")) {
            deviceDataService.fromPlat16(message);
        }else if(topic.equals("devicedata")){
              deviceDataService.devicedata(message);
        }else{
        	LOG.info(message);
        }
		
	}

}