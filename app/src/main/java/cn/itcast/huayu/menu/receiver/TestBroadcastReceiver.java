package cn.itcast.huayu.menu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.itcast.huayu.menu.util.ToastUtil;

/**
 * Created by hao on 2016/8/20.
 * 基于Android 6.0的源码剖析， 分析android广播的发送与接收流程。
 * http://gityuan.com/2016/06/04/broadcast-receiver/
 */
public class TestBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String mName = intent.getStringExtra("name");
        ToastUtil.showToast(context,mName);

    }
}
