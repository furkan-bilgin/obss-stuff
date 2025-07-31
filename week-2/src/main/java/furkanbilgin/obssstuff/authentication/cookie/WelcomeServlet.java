package furkanbilgin.obssstuff.authentication.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie-auth/welcome")
public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!LoginController.getInstance().isAuthenticated(request)) {
                response.sendRedirect("login");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/cookie-auth/welcome.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("login");
        }
    }
}
