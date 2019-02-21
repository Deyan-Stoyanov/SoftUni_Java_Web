package sbojgb.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/faces/jsf/create-job-application.xhtml",
        "/faces/jsf/delete.xhtml",
        "/faces/jsf/details.xhtml",
        "/faces/jsf/home.xhtml"
})
public class GuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("/faces/view/index.xhtml");
            return;
        }
        chain.doFilter(req, resp);
    }
}
