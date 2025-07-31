package furkanbilgin.obssstuff.authentication.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session-auth/login")
public class LoginServlet extends AuthServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!LoginController.getInstance().loginUser(request, request.getParameter("username"), request.getParameter("password"))) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/session-auth/login.jsp").forward(request, response);
        } else {
            // Redirect to welcome page after successful login
            response.sendRedirect(request.getContextPath() + "/session-auth/welcome");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/session-auth/login.jsp").forward(request, response);
    }

    @Override
    protected boolean shouldBeAuthenticated() {
        return false; // LoginServlet should not require authentication
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // If the user is already authenticated, redirect to welcome page
        if (LoginController.getInstance().isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/session-auth/welcome");
        } else {
            super.service(request, response);
        }
    }
}
