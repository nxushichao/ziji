//package com.newwechat;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.Gson;
//
//
//@Controller
//@RequestMapping("/login")
//public class WeChatCont2 {
//	String WX_SCAN_CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid={APPID}&redirect_uri={REUTL}&response_type=code&scope=snsapi_login&state={STATE}#wechat_redirect";
//	String WX_PLATFROM_APPID = "XXXXXX";
//	String WX_PLATFORM_APPSECRET = "XXXXXXXXXX";
//	// 拉起微信扫码页地址
//	String WX_SCAN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//	// 微信扫码之后获取用户基本信息的地址
//	String WX_SCAN_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
//	// 回调地址
//	String scanReUrl = "http://你的网址/user/wxLoginCallback";
//
//	@RequestMapping(value = "weixinScanLogin", method = RequestMethod.GET)
//	public void weixinRetrun(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// 获取回调url(非必填，只是附带上你扫码之前要进入的网址，具体看业务是否需要)
//		String url = request.getParameter("reurl");
//		// 拼接扫码登录url
//		String wxLoginurl = WX_SCAN_CODE_URL;
//		wxLoginurl = wxLoginurl.replace("{APPID}", WX_PLATFROM_APPID).replace("{REUTL}", scanReUrl).replace("{STATE}",
//				url);
//		wxLoginurl = response.encodeURL(wxLoginurl);
//		response.sendRedirect(wxLoginurl);
//	}
//	
//	@RequestMapping(value = "wxLoginCallback", method = RequestMethod.GET)
//    public void loginCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String code = request.getParameter("code");
//        if (code == null) {
//            // 用户禁止授权
//        }
//        String url = WX_SCAN_URL.replace("APPID", WX_PLATFROM_APPID).replace("SECRET", WX_PLATFORM_APPSECRET)
//                .replaceAll("CODE", code);
//        url = response.encodeURL(url);
//        try {
//        	JSONObject result = HttpClientUtils.httpGet(url);
//            Map<String, Object> resultMap = JsonHelper.toMap(result);
//            String unionid = (String) resultMap.get("unionid");
//            String access_token = (String) resultMap.get("access_token");
//            String openid = (String) resultMap.get("openid");
//            // 这里可以根据获取的信息去库中判断是否存在库中 如果不存在执行以下方法
//            // 如果该用户不存在数据库中
//            // 获取用户信息
//            url = ConstantHelper.WX_SCAN_GET_USER_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
//            url = response.encodeURL(url);
//            JSONObject userResult = HttpClientUtils.httpGet(url);
//            Map<String, Object> userResultMap = JsonHelper.toMap(userResult);
//            // 把用户信息存入session中
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 返回地址
//        try {
//            String newUrl = request.getParameter("state");
//            response.sendRedirect(newUrl);
//        } catch (IOException e) {
//            // logger.error("url:重定向错误");
//        }
//    }
//}
