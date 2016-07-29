package cn.itcast.huayu.hellowworld.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.rest.RestService;

import cn.itcast.huayu.hellowworld.MyApplication;
import cn.itcast.huayu.hellowworld.network.WeatherService;

/**
 * 处理Activity公共特性
 * Created by xu on 2015-06-09.
 */
@EActivity
@WindowFeature(Window.FEATURE_NO_TITLE)
public abstract class BaseActivity
		extends Activity
{
	@App
	public MyApplication mApp;
	@RestService
	protected WeatherService Service;
	//网络进度条
	private Dialog netLoadingDialog;

	/**
	 * 服务调用。
	 * Background:
	 * try{ result = mRemote.getSomething(param);}
	 * catch(...){
	 * }
	 * parse result
	 */
//	@RestService
//	protected PdaService mRemote;




	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}








	/**
	 * 点击返回
	 */
	public void clickBack()
	{
		finish();
	}




	/**
	 * 用户触发需要网络的操作时，即时检测网络状态，如果网络不可用可以阻挡进一步调用。
	 * 用法：
	 * if (alertNetworkUnavailable())
	 * return null;
	 *
	 * @return true-网络不可用
	 */
	public boolean alertNetworkUnavailable()
	{
		// 检查网络是否可用
		if (isNetworkConnected(this))
			return false;    // 可用返回false-网络可用
		// 提示不可用
		showToast("网络不可用");    // TODO: 修改提示方式
		return true;    // true-网络不可用
	}

	public static boolean isNetworkConnected(Context context)
	{
		try
		{
			// 判断网络情况
			if (context != null)
			{
				// 链接管理器
				ConnectivityManager mConnectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mNetworkInfo = mConnectivityManager
						.getActiveNetworkInfo();
				// 返回网络状态
				if (mNetworkInfo != null)
				{
//					lowRate = mNetworkInfo.getType()==;

					return mNetworkInfo.isAvailable();
				}
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return false;
	}

	/**
	 * 显示异常
	 *
	 * @param exception
	 */
	@UiThread
	public void showToast(Exception exception)
	{
		Toast.makeText(this, exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示短时间消息
	 *
	 * @param msg
	 */
	@UiThread
	public void showToast(String msg)
	{
		if(msg !=null){
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}


	/**
	 * 显示长时间消息
	 *
	 * @param msg
	 */
	@UiThread
	public void longToast(String msg)
	{
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}




}
