package com.config;

import com.entity.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * 拦截器
 * @Author:谁
 * @Date:2021/4/14 15:31
 */
public class Interceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        List<UserBean> user = (List<UserBean>)session.getAttribute("user");
        System.out.println("user："+user);
        if(user==null){
            //request.getRequestDispatcher("/index.html").forward(request, response);
            response.sendRedirect("/index.html");
        }else {
            List<String> userUrls = (List<String>)session.getAttribute("userUrls");
            if(userUrls!=null){
                if(userUrls.contains(uri)){
                    return true;
                }else{
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
                    out.println("<HTML>");
                    out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
                    out.println("  <BODY>");
                    out.println("<script>alert('哥们做人老实不能非法访问，小心有人请你喝茶');</script>");
                    out.println("  </BODY>");
                    out.println("</HTML>");
                    out.flush();
                    out.close();
                    request.getRequestDispatcher("/pages/main.html").forward(request, response);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
