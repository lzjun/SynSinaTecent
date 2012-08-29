package com.lzjun.weibo.tecent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;

import com.tencent.weibo.api.Private_API;
import com.tencent.weibo.api.T_API;
import com.tencent.weibo.api.User_API;
import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;
import com.tencent.weibo.utils.WeiBoConst;

public class Access extends HttpServlet {

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
		String verify = request.getParameter("oauth_verifier");
		if(verify!=null&&!"".equals(verify))
			tecent(request, response);
		else
			sina(request, response);
		request.getRequestDispatcher("synfa.jsp").forward(request, response);

	}

	private void tecent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OAuth oauth = (OAuth)session.getAttribute("oauth");
		String verify = request.getParameter("oauth_verifier");
		oauth.setOauth_verifier(verify);
		User_API user = new User_API();
		try {
			OAuthClient authClient=new OAuthClient();
			authClient.accessToken(oauth);
			System.out.println(user.info(oauth, "json"));

			Private_API private_API = new Private_API();

			String a = private_API.add(oauth,
					WeiBoConst.ResultType.ResultType_Json, "hello",
					"127.0.0.1", "", "", "hlwd_lzjun");
			System.out.println("response:" + a);

			T_API t = new T_API();
			
			String content = t.show(oauth, "json", "125048039799737");
			System.out.println(content);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//sinaOauth
		HttpSession session = request.getSession();
		Oauth oauth = (Oauth)session.getAttribute("sinaOauth");
		String code = request.getParameter("code");
		try {
			if(oauth==null)
				oauth = new Oauth();
			oauth.getAccessTokenByCode(code);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
