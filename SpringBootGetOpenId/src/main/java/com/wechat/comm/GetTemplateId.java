package com.wechat.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.utils.CommonUtil;

import net.sf.json.JSONObject;

public class GetTemplateId {
	private static Logger log = LoggerFactory.getLogger(WXGetToken.class);

	public static String getWXTemplateId() {
		String access_token = WXGetToken.getWXToken();
		String templateId;
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + access_token;
		JSONObject jsonObject = CommonUtil.httpsRequest(tokenUrl, "POST", null);
		if (jsonObject.has("template_id")) {
			templateId = jsonObject.getString("template_id");
		} else {
			templateId = null;
			log.error("获取template_id失败 errcode:{} errmsg:{}", jsonObject.getString("errmsg"));
		}
		return templateId;
	}
}
