package cn.itcast.huayu.hellowworld.util;

public class AppUtil {

    /**
     * 接口调用返回状态：成功
     */
    public static String EXCEPTION_STATUS_SUCCESS = "0";

    /**
     * 接口调用返回状态：业务异常
     */
    public static String EXCEPTION_STATUS_BUSINESS_ERROR = "1";

    /**
     * 接口调用返回状态：系统异常
     */
    public static String EXCEPTION_STATUS_SYSTEM_ERROR = "2";

    /**
     * 后台接口调用异常
     */
    public static String CALL_INTERFACE_EXCEPTION = "调用后台接口异常";

    /**
     * 传递数据错误异常
     */
    public static String REQUEST_PARAMETERS_EXCEPTION = "请求参数异常";

    /**
     * 验证码过期或错误异常
     */
    public static String REQUEST_VALIDATECODE_EXCEPTION = "验证码过期或输入错误";

    public static String NET_CONNECTION_EXCEPTION_ERR_CODE = "NET_CONNECTION_EXCEPTION";

    /**
     * 用户积分类型
     */
    public static String USER_INTEGRAL_SIGNIN = "1"; //签到
    public static int USER_INTEGRAL_SIGNIN_NUM = 10; //签到的积分数
    private static long lastClickTime;//第二次点击时间


    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= 500 ? true : false;
    }
}
