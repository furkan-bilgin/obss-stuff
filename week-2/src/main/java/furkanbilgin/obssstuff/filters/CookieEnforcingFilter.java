package furkanbilgin.obssstuff.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/filter/cookie/*") // Apply to all requests
public class CookieEnforcingFilter extends HttpFilter {
    private static final List<String> REQUIRED_COOKIES = List.of("test1", "test2");
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        for (String cookieName : REQUIRED_COOKIES){
            if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> cookie.getName().equals(cookieName))) {
                // Send error message
                response.setContentType("text/plain");
                response.getWriter().write("Missing required cookie: " + cookieName);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
