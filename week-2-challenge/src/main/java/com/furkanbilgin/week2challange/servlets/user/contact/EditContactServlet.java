package com.furkanbilgin.week2challange.servlets.user.contact;

import com.furkanbilgin.week2challange.controllers.AuthController;
import com.furkanbilgin.week2challange.controllers.DatabaseController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/contact/edit")
public class EditContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactId = request.getParameter("id");
        request.setAttribute(
                "contact",
                DatabaseController.getCrud().getContactById(
                        AuthController.INSTANCE.getUser(request).getId(),
                        Integer.parseInt(contactId)
                ));
        request.getRequestDispatcher("/WEB-INF/views/user/contact/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactId = request.getParameter("id");
        DatabaseController.getCrud().updateContact(
                Integer.parseInt(contactId),
                request.getParameter("contactName"),
                request.getParameter("contactNumber"),
                AuthController.INSTANCE.getUser(request).getId()
        );
        response.sendRedirect(request.getContextPath() + "/user/index");
    }
}
