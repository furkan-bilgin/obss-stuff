package com.furkanbilgin.week2challange.servlets.user.contact;

import com.furkanbilgin.week2challange.controllers.AuthController;
import com.furkanbilgin.week2challange.controllers.DatabaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/contact/delete")
public class DeleteContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var user = AuthController.INSTANCE.getUser(request);
        DatabaseController.getCrud().deleteContact(
                user.getId(),
                Integer.parseInt(request.getParameter("id"))
        );
        response.sendRedirect(request.getContextPath() + "/user/index");
    }
}
