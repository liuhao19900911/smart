package com.smart.control.device.service;

/**
 * 车机服务
 * @author LH
 * 2018年1月23日 11:13:04
 */
public interface DeviceDataService {

	/**
	 * 上报租车
	 * @param message
	 */
	public void fromPlat11(String message);
	
	/**
	 * 上报还车
	 * @param message
	 */
	public void fromPlat12(String message);
	
	/**
	 * 增删黑名单回复
	 * @param message
	 */
	public void fromPlat13(String message);
	
	/**
	 * 上报车上刷卡动作(刷卡方式)
	 * @param message
	 */
	public void fromPlat14(String message);
	
	/**
	 * 上报车辆状态
	 * @param message
	 */
	public void fromPlat15(String message);
	
	/**
	 * 上线校验
	 * @param message
	 */
	public void fromPlat16(String message);
	
	/**
	 * 数据存储
	 * @param message
	 */
	public void devicedata(String message);
}
