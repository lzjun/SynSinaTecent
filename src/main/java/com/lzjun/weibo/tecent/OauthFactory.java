package com.lzjun.weibo.tecent;

import weibo4j.util.WeiboConfig;

import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;


/**
 * 认证工厂
 * @author lzjun
 */

public class OauthFactory {

	//认证实体
	private static OAuth oauth;
	
	//认证授权实体
	private static OAuthClient authClient;

	private  OauthFactory() {
	}
	
	public static OAuth buildOAuth(){
		
		if(oauth==null){
			oauth=new OAuth(WeiboConfig.getValue("tecent_oauth_consumer_key"),  //appkey
				WeiboConfig.getValue("tecent_oauth_consumer_secret"),  //appSecret
				WeiboConfig.getValue("tecent_oauth_callback")); // 回调URL 
		}
		return oauth;
		
	}
	
	public static OAuthClient buildOauthClient(){
		if(authClient==null){
			 authClient=new OAuthClient();
		}
		return authClient;
	}
}
