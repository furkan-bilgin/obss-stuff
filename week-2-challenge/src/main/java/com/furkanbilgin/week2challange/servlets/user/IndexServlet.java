package com.furkanbilgin.week2challange.servlets.user;

import com.furkanbilgin.week2challange.controllers.AuthController;
import com.furkanbilgin.week2challange.controllers.DatabaseController;
import com.furkanbilgin.week2challange.models.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var userId = AuthController.INSTANCE.getUser(request).getId();
        List<Contact> contacts;
        if (request.getParameter("search") != null) {
            String searchQuery = request.getParameter("search");
            contacts = DatabaseController.getCrud().searchContacts(userId, searchQuery);
        } else {
            contacts = DatabaseController.getCrud().getContacts(userId);
        }
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(request, response);
    }
}
