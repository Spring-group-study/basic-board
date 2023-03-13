package com.study.board.login.interceptor;

import com.study.board.login.session.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            System.out.println("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/login?redirectURI=" + requestURI);
            //쿼리파라미터로 넘겨서 redirect -> 컨트롤러에서 처리하도록 개발
            return false;
        }
        return true;
    }
}
