package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityValidator {
    public void validateLoggedIn(HttpServletRequest req, HttpServletResponse resp, String route) throws IOException {
        if(req.getSession().getAttribute("username") != null){
            resp.sendRedirect(route);
        }
    }

    public void validateLoggedOut(HttpServletRequest req, HttpServletResponse resp, String route) throws IOException {
        if(req.getSession().getAttribute("username") == null){
            resp.sendRedirect(route);
        }
    }
}
