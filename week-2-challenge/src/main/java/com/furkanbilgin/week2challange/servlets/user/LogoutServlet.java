package com.furkanbilgin.week2challange.servlets.user;

import com.furkanbilgin.week2challange.controllers.AuthController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        AuthController.INSTANCE.logout(request);
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
