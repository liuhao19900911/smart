package com.smart.control.kafka.service;

import java.util.Map;

/**
 * Kafka 生产者 服务
 * @author LH
 *
 */
public interface KafkaProducerServer {
	
	/**
	 * 生产者 发布消息 LH 
	 * @param topic 主题
	 * @param value 值
	 * @param ifPartition 分区
	 * @param partitionNum 分区数
	 * @param role 角色
	 * @return
	 */
	public Map<String,Object> sndMesForTemplate(String topic, Object value, String ifPartition,Integer partitionNum, String role);
}
