package com.newwechat;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/login")
@Slf4j
public class WeChatCont {
	String appid="wx90a6a11aff2ba575";
	String callBack="http://mc62ut.natappfree.cc/login/callBack";
	String scope="snsapi_login";
	String appsecret="9fdaae5f3593571283d9811b10b79ee3";

	@RequestMapping("/login1")
	public String index(Model model) throws UnsupportedEncodingException {
		System.out.println(11);
		String oauthUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String redirect_uri = URLEncoder.encode(callBack, "utf-8");
		oauthUrl = oauthUrl.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", scope);
		model.addAttribute("name", "liuzp");
		model.addAttribute("oauthUrl", oauthUrl);
		return "index";
	}
	
	@RequestMapping("/callBack")
    public String callBack(String code,String state,Model model) throws Exception{
        log.info("进入授权回调,code:{},state:{}",code,state);
        //1.通过code获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID",appid).replace("SECRET",appsecret).replace("CODE",code);
        JSONObject tokenInfoObject =  HttpClientUtils.httpGet(url);
       // log.info("tokenInfoObject:{}",tokenInfoObject);
        //2.通过access_token和openid获取用户信息
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN",tokenInfoObject.getString("access_token")).replace("OPENID",tokenInfoObject.getString("openid"));
        JSONObject userInfoStr =  HttpClientUtils.httpGet(userInfoUrl);
        //log.info("userInfoObject:{}",userInfoStr);
        model.addAttribute("tokenInfoObject",tokenInfoObject);
        model.addAttribute("userInfoObject",userInfoStr);
        return "result";
    }
}
