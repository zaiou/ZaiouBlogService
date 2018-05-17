package com.zclient.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: 防止浏览器缓存页面或请求结果
 * @Author: liubin
 * @Date: Created in 17:35 2018/3/1
 * @Modified by:
 */
public class CacheControlFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie cookie[] = req.getCookies();
        if (req.getRequestURI().contains("checkcookie")) { //TODO  检查是否存在缓存，不存在的话跳转到404页面
            if (cookie == null || cookie.length <= 0) {
                resp.addCookie(new Cookie("addcookie", "lasttimeyoudonthavecookie"));
                resp.sendRedirect("404.jsp");
                return;
            }
        }
//      resp.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT"); 告诉浏览器数据可以缓存多长时间，-1或0表示不缓存
        resp.setDateHeader("Last-Modified", new Date().getTime());
//      resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0"); //支持HTTP 1.1，告诉浏览器要不要缓存数据，如“no-cache”
        resp.setHeader("Cache-Control", "max-age=20, private");
//      resp.setHeader("Pragma", "no-cache"); 支持HTTP 1.0，告诉浏览器要不要缓存数据，如“no-cache”

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
