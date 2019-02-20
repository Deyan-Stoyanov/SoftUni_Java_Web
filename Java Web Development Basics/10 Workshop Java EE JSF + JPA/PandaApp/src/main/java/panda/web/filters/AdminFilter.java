package panda.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/packages/create", "/packages/pending", "/packages/shipped", "/packages/delivered"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("admin") == null) {
            resp.sendRedirect("/");
            return;
        } else if(!((boolean) req.getSession().getAttribute("admin"))){
            resp.sendRedirect("/home");
            return;
        }
        chain.doFilter(req, resp);
    }
}
