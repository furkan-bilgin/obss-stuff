package com.furkanbilgin.week2challange.servlets.user.contact;

import com.furkanbilgin.week2challange.controllers.AuthController;
import com.furkanbilgin.week2challange.controllers.DatabaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/contact/create")
public class CreateContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/user/contact/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseController.getCrud().createContact(
                request.getParameter("contactName"),
                request.getParameter("contactNumber"),
                AuthController.INSTANCE.getUser(request).getId()
        );
        response.sendRedirect(request.getContextPath() + "/user/index");
    }
}
