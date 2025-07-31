package com.furkanbilgin.week2challange.filters;

import com.furkanbilgin.week2challange.controllers.AuthController;
import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/user/*")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws java.io.IOException, jakarta.servlet.ServletException {
       if (!AuthController.INSTANCE.isAuthenticated(request)) {
           response.sendRedirect(request.getContextPath() + "/login");
           return;
       }
       chain.doFilter(request, response);
    }
}
