package furkanbilgin.obssstuff.authentication.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (shouldBeAuthenticated() != LoginController.getInstance().isAuthenticated(request)
            && !request.getRequestURI().contains("/session-auth/login")) {
            response.sendRedirect(request.getContextPath() + "/session-auth/login");
        } else {
            super.service(request, response);
        }
    }

    protected boolean shouldBeAuthenticated() {
        return false;
    }
}
