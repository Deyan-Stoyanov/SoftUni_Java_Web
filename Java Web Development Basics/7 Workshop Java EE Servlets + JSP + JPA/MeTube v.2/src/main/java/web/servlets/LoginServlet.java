package web.servlets;

import domain.model.binding.UserLoginBindingModel;
import domain.model.service.UserServiceModel;
import service.UserService;
import util.ModelMapper;
import util.SecurityValidator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public LoginServlet(UserService userService, ModelMapper modelMapper, SecurityValidator securityValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel model = (UserLoginBindingModel) req.getAttribute("model");
        if(!this.userService.login(this.modelMapper.map(model, UserServiceModel.class))){
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("username", model.getUsername());
        req.getSession().setAttribute("admin", this.userService.isAdmin(model.getUsername()));
        resp.sendRedirect("/home");
    }
}
