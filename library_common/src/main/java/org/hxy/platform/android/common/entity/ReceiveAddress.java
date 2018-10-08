package org.hxy.platform.android.common.entity;

/**
 * @author 飞龙
 *
 */
public class ReceiveAddress {

	public int addressid;		//地址ID
	public String phone;		//电话号码
	public int provinceid;		//省份ID
	public String province;		//省份
	
	public int cityid;			//城市ID
	public String city;			//城市
	
	public int districtid;		//区ID
	public String district;		//区
	
	public String street;		//街道
	public String address;		//详细地址
	
	public int defaultAddress;	//是否默认接收地址. 1: 默认, 0:不是默认
}
