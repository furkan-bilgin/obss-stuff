package furkanbilgin.obssstuff.basicState;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/basic-state/origin")
public class OriginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("originParameter1", 1);
       request.setAttribute("originParameter2", 2);
       request.getRequestDispatcher("/basic-state/target").forward(request, response);
    }
}
