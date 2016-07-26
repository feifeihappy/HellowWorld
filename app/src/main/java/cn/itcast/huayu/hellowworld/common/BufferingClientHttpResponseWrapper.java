package cn.itcast.huayu.hellowworld.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 高佳 on 2015/1/16.
 * 后台服务请求response封装类
 * 为解决从response多次读取body数据
 */
public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;

    private byte[] body;


    public BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
        this.response = response;
    }


    public HttpStatus getStatusCode() throws IOException {
        return this.response.getStatusCode();
    }

    public int getRawStatusCode() throws IOException {
        return this.response.getRawStatusCode();
    }

    public String getStatusText() throws IOException {
        return this.response.getStatusText();
    }

    public HttpHeaders getHeaders() {
        return this.response.getHeaders();
    }

    public InputStream getBody() throws IOException {
        if (this.body == null) {
            this.body = FileCopyUtils.copyToByteArray(this.response.getBody());
        }
        return new ByteArrayInputStream(this.body);
    }

    public void close() {
        this.response.close();
    }

}