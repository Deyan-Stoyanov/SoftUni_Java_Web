package web.filters;

import domain.model.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class UserRegisterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getMethod().toUpperCase().equals("POST")){
            UserRegisterBindingModel model = new UserRegisterBindingModel(
                    req.getParameter("username"),
                    req.getParameter("password"),
                    req.getParameter("confirmPassword"),
                    req.getParameter("email")
            );
            req.setAttribute("model", model);
        }
        chain.doFilter(req, resp);
    }
}
