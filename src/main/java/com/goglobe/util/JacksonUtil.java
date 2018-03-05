package com.goglobe.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static ObjectMapper objectMapperWithoutNull = new ObjectMapper();
	
	static {
		objectMapperWithoutNull.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * json转java对象
	 * 
	 * @param args
	 */
	public static <T> T json2Obj(String json, Class<T> t) {
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		T obj = null;
		try {
			obj = objectMapper.readValue(json, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * java对象转json
	 * 
	 * @param obj
	 */
	public static String obj2Json(Object obj) {
		// ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static String objToJsonWithoutNull(Object obj) {
		String value = "";
		try {
			value = objectMapperWithoutNull.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> objArrayToParticularBeanList(List<Object> objList, Class<T> t) {
		try {
			String json = objectMapper.writeValueAsString(objList);

			JavaType listType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, List.class, t);
			return (List<T>) objectMapper.readValue(json, listType);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T1, T2> Map<T1, T2> jsonToParticularBeanMap(String json, Class<T1> keyClass, Class<T2> valueClass) {
		try {
			JavaType mapType = objectMapper.getTypeFactory().constructParametricType(Map.class, LinkedHashMap.class,
					keyClass, valueClass);
			return (Map<T1, T2>) objectMapper.readValue(json, mapType);
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
	 * 将数组的字符串形式转为指定类型的list
	 * 
	 * @param arrayString
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> arrayStringToParticularBeanList(String arrayString, Class<T> t) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, List.class, t);
			return (List<T>) objectMapper.readValue(arrayString, javaType);
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
	 * 将map的字符串形式转为指定key,value类型的map
	 * 
	 * @param mapString
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T1, T2> Map<T1, T2> mapStringToMapWithParticularKeyValue(String mapString, Class<T1> keyClass,
			Class<T2> valueClass) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(LinkedHashMap.class, Map.class,
					keyClass, valueClass);
			return (Map<T1, T2>) objectMapper.readValue(mapString, javaType);
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
	 * 输入obj，转化成json String，再转化成制定的class对象
	 * 
	 * @param obj
	 * @param t
	 * @return
	 */
	public static <T> T objToObjByJson(Object obj, Class<T> t) {
		return json2Obj(obj2Json(obj), t);
	}

	/**
	 * @description 在A中不在B中元素
	 * @author chang
	 * @param listA
	 * @param listB
	 * @return boolean标记A中元素是否在B中，true在B中，false不在B中
	 */
	public static List<Boolean> intersectList(List<String> listA, List<String> listB) {
		List<Boolean> boollist = new ArrayList<>();
		for (String str : listA) {
			if (listB.contains(str)) {
				boollist.add(true);
			} else {
				boollist.add(false);
			}
		}
		return boollist;
	}

	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int page = (int) Math.ceil((double) list.size() / pageSize);
		List<List<T>> listArray = new ArrayList<>();
		for (int i = 0; i < page; i++) {
			if (i == (page - 1)) {
				listArray.add(list.subList(pageSize * i, list.size()));
			} else {
				listArray.add(list.subList(pageSize * i, pageSize * (i + 1)));
			}
		}
		return listArray;
	}
}
