package furkanbilgin.obssstuff.authentication.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session-auth/welcome")
public class WelcomeServlet extends AuthServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/session-auth/welcome.jsp").forward(request, response);
    }

    @Override
    protected boolean shouldBeAuthenticated() {
        return true; // WelcomeServlet should require authentication
    }
}
