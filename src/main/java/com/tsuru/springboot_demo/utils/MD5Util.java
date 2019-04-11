package com.tsuru.springboot_demo.utils;

import java.security.MessageDigest;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public class MD5Util {

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
