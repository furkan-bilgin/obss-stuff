package com.furkanbilgin.week2challange.servlets;

import com.furkanbilgin.week2challange.controllers.AuthController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/login/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (AuthController.INSTANCE.authUser(request, username, password)) {
            response.sendRedirect(request.getContextPath() + "/user/index");
            return;
        }
        request.setAttribute("errorMessage", "Wrong username or password");
        doGet(request, response);
    }
}
