package furkanbilgin.obssstuff.cookieAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookie-auth/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/cookie-auth/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !LoginController.getInstance().loginUser(username, password)) {
            // Add error
            request.setAttribute("errorMessage", "Invalid username or password.");
            doGet(request, response);
        } else {
            // Set cookie and redirect to welcome page
            LoginController.getInstance().setUserSecret(response, username);
            response.sendRedirect(request.getContextPath() + "/cookie-auth/welcome");
        }
    }

    /// Authorization middleware (I guess you could call it a "middleware")
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            if (LoginController.getInstance().isAuthenticated(request)) {
                response.sendRedirect(request.getContextPath() + "/cookie-auth/welcome");
                return;
            }
        } catch (IllegalStateException ignored) { }
        super.service(request, response);
    }
}
