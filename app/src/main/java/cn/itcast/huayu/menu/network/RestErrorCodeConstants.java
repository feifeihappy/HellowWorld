package cn.itcast.huayu.menu.network;

/**
 * @author：高佳
 * @create：2015年5月20日 下午1:00:04
 * @description：Rest接口返回错误码常量类
 */
public class RestErrorCodeConstants {
	
	/**
	 * 成功状态码
	 */
	public static final String STATUS_SUCCESS = "1000";
	public static final String STATUS_SUCCESS_TOW = "200";

	public static final String STATUS_SUCCESS_INFO = "成功";
	/**
	 * 权限错误状态码
	 */
	public static final String STATUS_PERMISSION_ERROR = "2001";
	public static final String STATUS_PERMISSION_ERROR_INFO = "访问权限错误，白名单验证错误";
	/**
	 * 数据安全错误状态码
	 */
	public static final String STATUS_SECURITY_ERROR = "2002";
	public static final String STATUS_SECURITY_ERROR_INFO = "数据安全错误，摘要验证错误或是时间戳验证超时";
	/**
	 * 参数错误状态码
	 */
	public static final String STATUS_PARAMETER_ERROR = "3001";
	public static final String STATUS_PARAMETER_ERROR_INFO = "参数错误";
	/**
	 * 业务规则错误状态码
	 */
	public static final String STATUS_BUSSINESS_RULE_ERROR = "3002";
	public static final String STATUS_BUSSINESS_RULE_ERROR_INFO = "业务规则判断错误";
	/**
	 * 业务异常状态码
	 */
	public static final String STATUS_BUSSINESS_ERROR = "8000";
	public static final String STATUS_BUSSINESS_ERROR_INFO = "业务异常";
	/**
	 * 未知错误状态码
	 */
	public static final String STATUS_UNKNOWN_ERROR = "9000";
	public static final String STATUS_UNKNOWN_ERROR_INFO = "未知错误";
	/**
	 * 系统异常状态码
	 */
	public static final String STATUS_SYSTEM_ERROR = "9999";
	public static final String STATUS_SYSTEM_ERROR_INFO = "系统异常";
}
