package cn.itcast.huayu.hellowworld.network;

import org.apache.http.client.HttpClient;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * Created by admin on 2015/9/23.
 */
public class MyHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (40 * 1000);

    public MyHttpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        super(httpClient);
    }

    public MyHttpComponentsClientHttpRequestFactory() {
        super();
        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
    }
}
