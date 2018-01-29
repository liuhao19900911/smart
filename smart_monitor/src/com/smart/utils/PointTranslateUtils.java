package com.smart.utils;

import java.awt.geom.Point2D;

/**
 * 地图转换工具类
 *  -偏差稍小
 * Created by LH on 2016-12-02.
 */
public class PointTranslateUtils {

    //圆周率
    private static double pi = 3.14159265358979324;
    //卫星椭球坐标投影到平面地图坐标系的投影因子
    private static double a = 6378245.0;
    //椭球的偏心率
    private static double ee = 0.00669342162296594323;
    //圆周率转换量
    private final static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    

    
    /**
     * 坐标类型介绍：
     * WGS-84：是国际标准，GPS坐标（Google Earth使用、或者GPS模块）
     * GCJ-02：中国坐标偏移标准，Google Map、高德、腾讯使用
     * BD-09：百度坐标偏移标准，Baidu Map使用
     */

    /**
     * WGS-84 转 百度(BD-09)
     * @param lon   -经度
     * @param lat   -纬度
     * @return Point2D.Double x:经度；y:纬度
     */
    public static Point2D.Double wgs2bd(double lon,double lat) {
    	if(outOfChina(lon, lat)){
    		return new Point2D.Double(0,0);
    	}
        Point2D.Double wgs2gcj = wgs2gcj(lon,lat);
        Point2D.Double gcj2bd = gcj2bd(wgs2gcj.x, wgs2gcj.y);
        return  gcj2bd;
    }

    /**
     * GCJ-02 转 百度(BD-09)
     * @param lon   -经度
     * @param lat   -纬度
     * @return Point2D.Double x:经度；y:纬度
     */
    public static Point2D.Double gcj2bd(double lon, double lat) {
    	if(outOfChina(lon, lat)){
    		return new Point2D.Double(0,0);
    	}
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return  new Point2D.Double(bd_lon,bd_lat);
    }

    /**
     * 百度(BD-09) 转 GCJ-02
     * @param lon   -经度
     * @param lat   -纬度
     * @return Point2D.Double x:经度；y:纬度
     */
    public static Point2D.Double bd2gcj(double lon,double lat) {
    	if(outOfChina(lon, lat)){
    		return new Point2D.Double(0,0);
    	}
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return  new Point2D.Double(gg_lon,gg_lat);
    }

    /**
     * WGS-84 转 GCJ-02
     * @param lon   -经度
     * @param lat   -纬度
     * @return Point2D.Double x:经度；y:纬度
     */
    public static Point2D.Double wgs2gcj(double lon,double lat) {
    	if(outOfChina(lon, lat)){
    		return new Point2D.Double(0,0);
    	}
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return  new Point2D.Double(mgLon,mgLat);
    }

    private static double transformLat(double x,double y) {
    	double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
    	 double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
         ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
         ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
         ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
         return ret;
    }
    
    /**
     * 判断是否在国内
     * @param lng
     * @param lat
     * @return
     */
    private static boolean outOfChina (double lng, double lat) {
        return (lng < 72.004 || lng > 137.8347) || ((lat < 0.8293 || lat > 55.8271) || false);
    }

}
