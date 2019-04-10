package com.tsuru.springboot_demo.utils;

import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MD5Util {

	protected static Logger log = LogManager.getLogger(MD5Util.class);

	public static String encode(String value) {
		try {
			//System.out.println(Charset.defaultCharset());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(value.getBytes("utf-8"));
			byte[] encryContext = md5.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
				i = encryContext[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
