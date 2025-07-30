package furkanbilgin.obssstuff;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/increment-value")
public class IncrementValueServlet extends HttpServlet {
    private int value = 0;

    protected void doGet(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        synchronized (this) {
            value++;
        }
        response.setContentType("text/plain");
        response.getWriter().println("Current value: " + value);
    }
}
