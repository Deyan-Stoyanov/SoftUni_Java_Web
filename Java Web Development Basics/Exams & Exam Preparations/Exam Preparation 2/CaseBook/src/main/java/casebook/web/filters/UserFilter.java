package casebook.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/faces/view/index.xhtml",
        "/faces/view/register.xhtml",
        "/faces/view/login.xhtml",
        "/"
})
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("/faces/view/home.xhtml");
            return;
        }
        chain.doFilter(req, resp);
    }
}
