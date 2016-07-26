package cn.itcast.huayu.hellowworld.network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by 271755 on 2015/3/3.
 * 解析客户端与服务端交互传递的Json字符串
 * 忽略字符串与实体之间对应不上的属性
 */
public class JacksonIgnorePropertie2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public JacksonIgnorePropertie2HttpMessageConverter(){
        super();
        //忽略无对应字段属性
        getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public void setObjectMapper(ObjectMapper objectMapper) {
        //忽略无对应字段属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        super.setObjectMapper(objectMapper);
    }
}
