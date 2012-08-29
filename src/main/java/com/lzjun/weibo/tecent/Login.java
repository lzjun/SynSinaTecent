package com.lzjun.weibo.tecent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String vender = request.getParameter("vender");

		if ("tecent".equals(vender)) {
			tecent(request, response);
		} else if ("sina".equals(vender)) {
			sina(request, response);
		}
	}

	private void tecent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OAuthClient authClient = new OAuthClient();
			OAuth oauth = new OAuth(
					WeiboConfig.getValue("tecent_oauth_consumer_key"), // appkey
					WeiboConfig.getValue("tecent_oauth_consumer_secret"), // appSecret
					WeiboConfig.getValue("tecent_oauth_callback")); // 回调URL
			authClient.requestToken(oauth);

			HttpSession session = request.getSession(true);

			session.setAttribute("oauth", oauth);

			if (oauth.getStatus() == 1) {
				System.out.println("认证请求失败");
				return;
			} else {
				// 授权
				String oauth_token = oauth.getOauth_token();
				String url = "http://open.t.qq.com/cgi-bin/authorize?oauth_token="
						+ oauth_token;
				response.getWriter().write(url);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Oauth oauth = new Oauth();
			HttpSession session = request.getSession(true);
			session.setAttribute("sinaOauth", oauth);
			try {
				String url = oauth.authorize("code");
				response.getWriter().write(url);
			} catch (WeiboException e) {
				e.printStackTrace();
			}
			
		

	
	}

}
