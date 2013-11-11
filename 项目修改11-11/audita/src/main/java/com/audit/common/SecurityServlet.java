package com.audit.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.audit.entity.User;

public class SecurityServlet extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
			ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user"); // 获取登陆账户信息
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/common/errorsession.jsp");
		}
		String url = request.getRequestURI();
		if (user != null) {

			if (!Global.FunctionListEquals(url)) {
				String[] newUrl = url.split("/");
				StringBuffer sbf = new StringBuffer("/");

				for (int i = 1; i <= newUrl.length - 2; i++) {
					sbf.append(newUrl[i] + "/");
				}

				if (user.getPermission().indexOf(sbf.toString()) != -1) {
					arg2.doFilter(arg0, arg1);
					return;
				}
			}

			String[] permission = user.getPermission().split(",");

			for (String s : permission) {
				if (s.indexOf(url) != -1) {
					arg2.doFilter(arg0, arg1);
					return;
				}
			}

			response.sendRedirect(request.getContextPath() + "/common/error.jsp");
			return;
		}

		// response.sendRedirect(request.getContextPath());
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
