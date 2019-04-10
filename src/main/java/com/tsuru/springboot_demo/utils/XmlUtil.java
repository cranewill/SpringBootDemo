package com.tsuru.springboot_demo.utils;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: XmlUtil
 * @Description:
 * @author: Tsuru
 */
@Log4j2
public class XmlUtil {

	/**
	 * 将map转化为xml字符串
	 * @param dataMap
	 * @return
	 */
	public static String converter(Map<String, Object> dataMap) {
		synchronized (XmlUtil.class) {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("<xml>");
			strBuilder.append("\n");
			Set<String> objSet = dataMap.keySet();
			for (String key : objSet) {
				if (key == null) {
					continue;
				}
				strBuilder.append("<").append(key).append(">");
				Object value = dataMap.get(key);
				strBuilder.append(coverter(value));
				strBuilder.append("</").append(key).append(">\n");
			}
			strBuilder.append("</xml>");
			return strBuilder.toString();
		}
	}


	/**
	 * 对map的value进行处理
	 * @param object
	 * @return
	 */
	public static String coverter(Object object) {
		if (object instanceof Object[]) {
			return coverter((Object[]) object);
		}
		if (object instanceof Collection) {
			return coverter((Collection<?>) object);
		}
		StringBuilder strBuilder = new StringBuilder();
		if (isObject(object)) {
			Class<? extends Object> clz = object.getClass();
			Field[] fields = clz.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				if (field == null) {
					continue;
				}
				String fieldName = field.getName();
				Object value = null;
				try {
					value = field.get(object);
				} catch (IllegalArgumentException e) {
					continue;
				} catch (IllegalAccessException e) {
					continue;
				}
				strBuilder.append("<").append(fieldName)
						.append(" className=\"").append(
						value.getClass().getName()).append("\">\n");
				if (isObject(value)) {
					strBuilder.append(coverter(value));
				} else if (value == null) {
					strBuilder.append("null\n");
				} else {
					strBuilder.append(value.toString() + "\n");
				}
				strBuilder.append("</").append(fieldName).append(">\n");
			}
		} else if (object == null) {
			strBuilder.append("null");
		} else {
			strBuilder.append(object.toString());
		}
		return strBuilder.toString();
	}

	/**
	 * 判断是否是复杂对象
	 * @param obj
	 * @return
	 */
	private static boolean isObject(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			return false;
		}
		if (obj instanceof Integer) {
			return false;
		}
		if (obj instanceof Double) {
			return false;
		}
		if (obj instanceof Float) {
			return false;
		}
		if (obj instanceof Byte) {
			return false;
		}
		if (obj instanceof Long) {
			return false;
		}
		if (obj instanceof Character) {
			return false;
		}
		if (obj instanceof Short) {
			return false;
		}
		if (obj instanceof Boolean) {
			return false;
		}
		return true;
	}
}
