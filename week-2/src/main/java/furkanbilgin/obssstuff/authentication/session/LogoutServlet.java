package furkanbilgin.obssstuff.authentication.session;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet("/session-auth/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        LoginController.getInstance().logoutUser(request);
        response.sendRedirect(request.getContextPath() + "/session-auth/login");
    }
}
