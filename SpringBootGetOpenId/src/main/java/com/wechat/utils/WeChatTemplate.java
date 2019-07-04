package com.wechat.utils;

import java.util.ArrayList;
import java.util.List;

import com.wechat.comm.GetTemplateId;
import com.wechat.comm.WXGetToken;
import com.wechat.pojo.Template;
import com.wechat.pojo.TemplateParam;

import net.sf.json.JSONObject;

public class WeChatTemplate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String token = WXGetToken.getWXToken();

		Template tem = new Template();
		tem.setTemplateId("2pUB16yCLI6NR8sqjZgSbVKtaZP_9QsYKOXEidBnnUM");
		tem.setTopColor("#00DD00");
		tem.setToUser("okRIo50yc0bXOnOztDKVRvF00T8Y");
		tem.setUrl("www.baidu.com");

		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "我们已收到您的货款，开始为您打包商品，请耐心等待: )", "#FF3333"));
		paras.add(new TemplateParam("orderProductPrice", "¥20.00", "#0044BB"));
		paras.add(new TemplateParam("orderProductName", "火烧牛干巴", "#0044BB"));
		paras.add(new TemplateParam("orderName", "2019年7月4号", "#0044BB"));
		paras.add(new TemplateParam("remark", "感谢你对我们商城的支持!!!!", "#AAAAAA"));

		tem.setTemplateParamList(paras);

		boolean result = sendTemplateMsg(token, tem);
	}

	public static boolean sendTemplateMsg(String token, Template template) {

		boolean flag = false;

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
		System.out.println(template.toJSON());
		JSONObject jsonResult = CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());
		if (jsonResult != null) {
			System.out.println(jsonResult.toString());
			int errorCode = jsonResult.getInt("errcode");
			String errorMessage = jsonResult.getString("errmsg");
			if (errorCode == 0) {
				flag = true;
			} else {
				System.out.println("模板消息发送失败:" + errorCode + "," + errorMessage);
				flag = false;
			}
		}
		return flag;

	}
}
