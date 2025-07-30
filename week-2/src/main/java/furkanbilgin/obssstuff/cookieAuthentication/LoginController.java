package furkanbilgin.obssstuff.cookieAuthentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

public class LoginController {
    private static LoginController instance;
    private final HashMap<Integer, String> userSecrets = new HashMap<>();
    private final List<User> users = new ArrayList<>();
    private final Random random = new Random();

    private LoginController() {
        users.add(new User("furkan", "1234"));
        users.add(new User("john", "abcd"));
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void storeLogin(String username, int secret) {
        userSecrets.put(secret, username);
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return userSecrets.containsKey(getRequestSecretCookie(request));
    }

    public void setUserSecret(HttpServletResponse response, String username) {
        int secret = random.nextInt();
        storeLogin(username, secret);
        response.addCookie(new Cookie("secret", String.valueOf(secret)));
    }

    public int getRequestSecretCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new IllegalStateException("No cookies found in the request.");
        }
        for (Cookie cookie : cookies) {
            if ("secret".equals(cookie.getName())) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        throw new IllegalStateException("Cookie not found in the request.");
    }
}
