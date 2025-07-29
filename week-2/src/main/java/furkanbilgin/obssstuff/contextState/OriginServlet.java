package furkanbilgin.obssstuff.contextState;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/context-state/origin")
public class OriginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add attributes to ServletContext
        getServletContext().setAttribute("contextParameter1", 1);
        getServletContext().setAttribute("contextParameter2", 2);
        // Forward to target
        request.getRequestDispatcher("/context-state/target").forward(request, response);
    }
}
