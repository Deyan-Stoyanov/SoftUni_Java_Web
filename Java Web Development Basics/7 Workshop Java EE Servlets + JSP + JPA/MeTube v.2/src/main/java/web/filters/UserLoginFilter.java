package web.filters;

import domain.model.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().toUpperCase().equals("POST")) {
            UserLoginBindingModel model = new UserLoginBindingModel(
                    req.getParameter("username"),
                    req.getParameter("password")
            );
            req.setAttribute("model", model);
        }
        chain.doFilter(req, resp);
    }
}
