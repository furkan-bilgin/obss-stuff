package furkanbilgin.obssstuff.stateManagement.contextState;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/context-state/target")
public class TargetServlet extends OriginServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer contextParameter1 = (Integer) getServletContext().getAttribute("contextParameter1");
        Integer contextParameter2 = (Integer) getServletContext().getAttribute("contextParameter2");

        response.getWriter().println("Got context parameters: " + contextParameter1 + ", " + contextParameter2);
    }
}
