package cn.itcast.huayu.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;

/**
 * @author ln：zpf on 2016/7/26
 */
public class WebViewActivity extends BaseActivity {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MenuDataVo mData = (MenuDataVo) getIntent().getSerializableExtra("mData");
        mWebView = (WebView) findViewById(R.id.webview);
        //打开网页
        mWebView.loadUrl("http://www.runoob.com/");
        // JavaScript使能(如果要加载的页面中有JS代码，则必须使能JS)
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 在WebView中打开链接（默认行为是使用浏览器，设置此项后都用WebView打开）
        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            // 返回键退回
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);

    }
}
