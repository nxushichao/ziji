package com.wechat.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.utils.CommonUtil;

import net.sf.json.JSONObject;

public class WXGetToken {
	private static Logger log = LoggerFactory.getLogger(WXGetToken.class);

	public static String getWXToken() {
		String appId = "wx978ee25dcb844019";
		String appSecret ="3de9d446850759dc6097ef4d9487a0f1";
		String access_token;
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
		JSONObject jsonObject = CommonUtil.httpsRequest(tokenUrl, "GET", null);
		if (jsonObject.has("access_token")) {
			access_token = jsonObject.getString("access_token");
			log.info(access_token);
		} else {
			access_token = null;
			log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getString("errmsg"));
		}
		return access_token;
	}
}
