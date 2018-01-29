package com.smart.control.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.control.device.service.DeviceDataService;
import com.smart.control.kafka.service.KafkaProducerServer;

@Controller
@RequestMapping("/sys")
public class SysController {
	 protected final Logger LOG = LoggerFactory.getLogger(SysController.class);
	@Autowired
	private KafkaProducerServer kafkaProducerServer;
	@Autowired
	private DeviceDataService deviceDataService;
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		String topic = "test3";
        String value = "test";
        String ifPartition = "1";
        Integer partitionNum = 3;
        String role = "test";//用来生成key
		kafkaProducerServer.sndMesForTemplate(topic, value, ifPartition, partitionNum, role);
		request.setAttribute("topic", topic);
		request.setAttribute("value", value);
		return "show";
	}
	
	@RequestMapping("/test2")
	public String test2(HttpServletRequest request) {/*
		String topic = "devicedata";
		String message="('f9a12b67beb04437baa42586d765cdf4', '10000001', 'null', 'null', 'null', '120.1410529', '30.2708940', '0.00', '0.00', '120.1298400', '30.2669200', 'online', '0', '{\\\"450\\\":\\\"0000000000000000\\\",\\\"6A0\\\":\\\"0000000000000000\\\"}', null, '2017-11-25 02:00:00', '2017-11-25 01:08:01', '0.0', '-1', '-1');";
		String ifPartition = "1";
	    Integer partitionNum = 3;
	    String role = "test";//用来生成key
		deviceDataService.devicedata(message);
		//kafkaProducerServer.sndMesForTemplate(topic, message, ifPartition, partitionNum, role);
		request.setAttribute("topic", topic);
		request.setAttribute("value", message);*/
		LOG.info("1111111");
		LOG.error("11111111");
		LOG.debug("111");
		return "show";
	}
}
