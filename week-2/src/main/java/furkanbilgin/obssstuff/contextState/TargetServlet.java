package furkanbilgin.obssstuff.contextState;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/context-state/target")
public class TargetServlet extends OriginServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        Integer contextParameter1 = (Integer) request.getServletContext().getAttribute("contextParameter1");
        Integer contextParameter2 = (Integer) request.getServletContext().getAttribute("contextParameter2");

        response.getWriter().println("Got context parameters: " + contextParameter1 + ", " + contextParameter2);
    }
}
