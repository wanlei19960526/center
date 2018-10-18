package com.xn.hk.common.utils.encryption;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xn.hk.common.utils.cfg.SystemCfg;

/**
 * 
 * @Title: Md5Util
 * @Package: com.xn.hk.common.utils
 * @Description: Md5操作工具类
 * @Author: wanlei
 * @Date: 2018年1月8日 下午4:44:15
 */
public class Md5Util {
	private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            加密前的字符串
	 * @return 加密后的字符串
	 */
	public final static String MD5(String str) {
		String result = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = str.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char dist[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				dist[k++] = hexDigits[byte0 >>> 4 & 0xf];
				dist[k++] = hexDigits[byte0 & 0xf];
			}
			result = new String(dist);
		} catch (Exception e) {
			logger.error("MD5加密失败，原因为:" + e);
		}
		return result;
	}

	/**
	 * 测试main方法
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("MD5加密之后的密码为：" + MD5("admin" + SystemCfg.USER_PWD_KEY));
	}
}
