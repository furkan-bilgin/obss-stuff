package furkanbilgin.obssstuff.authentication;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/auth/error")
public class ErrorService extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        out.println("Login failed.");
    }
}
