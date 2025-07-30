package furkanbilgin.obssstuff;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sample-jsp-2-service")
public class SampleJSP2Service extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("<p style=\"font-weight: bolder; color: cornflowerblue;\">" +
                "<a href=\"https://obss.tech\" target=\"_blank\">Go to OBSS</a>" +
                "</p>");
    }
}
