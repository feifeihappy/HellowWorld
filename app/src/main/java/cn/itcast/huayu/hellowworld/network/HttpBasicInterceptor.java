package cn.itcast.huayu.hellowworld.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

import cn.itcast.huayu.hellowworld.MyApplication;
import cn.itcast.huayu.hellowworld.common.BufferingClientHttpResponseWrapper;
import cn.itcast.huayu.hellowworld.common.BusinessException;
import cn.itcast.huayu.hellowworld.model.ResponseBaseEntity;
import cn.itcast.huayu.hellowworld.util.AppUtil;
import cn.itcast.huayu.hellowworld.util.JsonUtils;

/**
 * Created by 271755 on 2015/1/12.
 */
@EBean(scope = EBean.Scope.Singleton)
public class HttpBasicInterceptor implements ClientHttpRequestInterceptor {
    private static final String TAG = HttpBasicInterceptor.class.getSimpleName();
    @App
    MyApplication application;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //检测网络是否可用
        if (!checkNetWorkStatus(application)) {
            throw new BusinessException("当前网络连接未打开,请检查当前网络连接");
        }


//        request.getHeaders().set("loginName",BaseCache.userInfo.strUsrNo);
//        request.getHeaders().set("deptCode",BaseCache.userInfo.strCompanyID);
//        request.getHeaders().set("appVersion","0.0.1");
//        request.getHeaders().set("deviceId",BaseCache.userInfo.strDeviceID);

        ClientHttpResponse response = null;
        String responseBody = null;
        ResponseBaseEntity resBaseVo = null;
        try {
            response = execution.execute(request, body);
            response = new BufferingClientHttpResponseWrapper(response);
            responseBody = inputStream2String(response.getBody());

            Log.e("请求地址",request.getURI().toString() );
            Log.e("请求内容", new String(body));
            Log.e("响应内容", responseBody.toString());

            //处理服务器后台返回Json字符串
            resBaseVo = JsonUtils.toObject(responseBody, ResponseBaseEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage() == null ? "null" : e.getMessage());
            throw new BusinessException(AppUtil.NET_CONNECTION_EXCEPTION_ERR_CODE, "网络请求异常", e.getCause());
        }
        if (resBaseVo == null) {
            throw new BusinessException(AppUtil.NET_CONNECTION_EXCEPTION_ERR_CODE, "网络请求异常");
        }
        if ((RestErrorCodeConstants.STATUS_SUCCESS.equals(resBaseVo.getResultcode()))
                || (RestErrorCodeConstants.STATUS_SUCCESS_TOW.equals(resBaseVo.getResultcode()))) {
            //服务器返回成功状态
            return response;
        } else {
            //服务器非法错误码
            throw new BusinessException(resBaseVo.getReason());
        }
    }

    public String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    public boolean checkNetWorkStatus(Context context) {
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
