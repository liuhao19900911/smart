package com.smart.control.device.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.smart.config.MemcachedUtils;
import com.smart.control.device.dao.DeviceMapper;
import com.smart.control.device.service.DeviceDataService;
import com.smart.utils.DateUtil;

@Service("deviceDataService")
public class DeviceDataServiceImpl implements DeviceDataService{

	private Logger log = Logger.getLogger(DeviceDataServiceImpl.class);
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void fromPlat11(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromPlat12(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromPlat13(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromPlat14(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromPlat15(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromPlat16(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devicedata(String message) {
		if (message != null && !"".equals(message)) {
			log.info(message);
			try {
				String tableName = getTableName(new Date());
				if (tableName != null && !"".equals(tableName)) {
					jdbcTemplate.execute("INSERT INTO " + tableName
							+ " (id,device_no,car_type,car_vin,car_no,lng,lat,soc,km,offset_lng,offset_lat,status,data_type,data,mac,recive_time,create_date,speed,acc_state,battery_state) VALUES "
							+ message);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	/**
	 * 获取当前数据表名
	 * @param date
	 * @return
	 */
	public String getTableName(Date date) {
		String dataTableName = DateUtil.getDeviceDataTableName(date);
		Object obj = MemcachedUtils.get(dataTableName);
		if(obj==null) {
			String deviceTableName = deviceMapper.getDeviceTableName(dataTableName);
			if(deviceTableName!=null && !deviceTableName.equals("")) {
				MemcachedUtils.set(dataTableName, true);
				return dataTableName;
			}
		}else {
			return dataTableName;
		}
		return null;
	}
}
