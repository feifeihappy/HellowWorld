package cn.itcast.huayu.hellowworld.util;



import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static <T> T toObject(String jsonStr,Class<T> t){
		if(jsonStr == null || "".equals(jsonStr)){
			return null;
		}
		try {
			return mapper.readValue(jsonStr, t);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toJson(Object o){
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * jsonToList<T> 带泛型
	 * jsonStr  
	 * clazz  集合元素类型
	 */
	
	public static <T> List<T> toList(String jsonStr,Class<T> clazz){
		JavaType javaType = getCollectionType(List.class,clazz);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * jsonToMap<T> 带泛型
	 * jsonStr  json字符串
	 * keyClazz     key class
	 * valueClazz 	value class
	 */
	public static <K,V> Map<K, V> toMap(String jsonStr,Class<K> keyClazz,Class<V> valueClazz){
		JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, keyClazz,valueClazz);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
}
