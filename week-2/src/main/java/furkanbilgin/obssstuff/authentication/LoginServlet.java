package furkanbilgin.obssstuff.authentication;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, NullPointerException {
        // Should be POST instead of GET
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null
                || password == null
                || !username.equals("admin")
                || !password.equals("admin")) {
            // Forward to /auth/error
            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/error");
            dispatcher.forward(request, response);
            return;
        }
        // Redirect to welcome page
        response.sendRedirect("/auth/welcome/here");
    }
}
