package furkanbilgin.obssstuff.authentication.session;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private static final String SESSION_KEY = "user";
    private static LoginController instance;
    private final List<User> users = new ArrayList<>();

    private LoginController() {
        users.add(new User("furkan", "1234"));
        users.add(new User("john", "abcd"));
    }

    public static synchronized LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute(SESSION_KEY) != null;
    }

    public boolean loginUser(HttpServletRequest request, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                request.getSession().setAttribute(SESSION_KEY, user);
                return true;
            }
        }
        return false;
    }

    public void logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
