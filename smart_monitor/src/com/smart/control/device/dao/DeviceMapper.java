package com.smart.control.device.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 车机服务
 * @author LH
 *
 */
public interface DeviceMapper {
	@Select("select car_vin as vin,car_type as type,car_plate_no as no from c_device_binding where bind_type=1 and device_no=#{deviceNo,jdbcType=VARCHAR}  ")
    public Map<String,String> queryDeviceBindByDeviceNo(@Param("deviceNo") String deviceNo);
	
    @Select("select a.table_name from s_table_name_data a where a.table_name=#{deviceNo,jdbcType=VARCHAR} ")
    public String getDeviceTableName(@Param("deviceNo") String deviceNo);
}
