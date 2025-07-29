package furkanbilgin.obssstuff.basicState;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/basic-state/target")
public class TargetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer originParameter1 = (Integer) request.getAttribute("originParameter1");
        Integer originParameter2 = (Integer) request.getAttribute("originParameter2");

        response.getWriter().println("Got parameters: " + originParameter1 + ", " + originParameter2);
    }
}
