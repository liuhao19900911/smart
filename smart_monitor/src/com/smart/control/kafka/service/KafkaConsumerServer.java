package com.smart.control.kafka.service;

public interface KafkaConsumerServer {

	/**
	 * 消息处理
	 * @param topic
	 * @param value
	 */
	public void handleMessage(String topic,String message);
}
