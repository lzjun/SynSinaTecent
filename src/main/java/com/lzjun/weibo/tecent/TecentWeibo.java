package com.lzjun.weibo.tecent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tencent.weibo.api.T_API;
import com.tencent.weibo.beans.OAuth;

public class TecentWeibo extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");

		String operator = request.getParameter("operator");
		if(null!=operator){
			
			if(operator.equals("send")){
				String content = request.getParameter("weiboContent");
				
				T_API t = new T_API();
				try {
					HttpSession session = request.getSession();

					OAuth oauth = (OAuth)session.getAttribute("oauth");
					String ret = t.add(oauth, "json", content, "192.168.0.100");
					System.out.println(ret);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			
		}
		
		
	}

}
