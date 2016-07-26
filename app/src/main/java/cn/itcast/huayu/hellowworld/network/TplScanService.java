package cn.itcast.huayu.hellowworld.network;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

import cn.itcast.huayu.hellowworld.model.ResponseBaseEntity;

/**
 * TPL服务接口
 * Created by xu on 2015-05-11.
 */

@Rest(rootUrl = IService.Url, converters = {JacksonIgnorePropertie2HttpMessageConverter.class}, interceptors = {PdascanHttpBasicInterceptor.class}, requestFactory = MyHttpComponentsClientHttpRequestFactory.class)
public interface TplScanService {

    //1、下拉计划
    @Get("/{cityname}/{key}")
    ResponseBaseEntity<Void> getScantPlan( String cityname,String key);



}
