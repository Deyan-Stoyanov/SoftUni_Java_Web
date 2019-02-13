package web.servlets;

import domain.model.binding.UserRegisterBindingModel;
import domain.model.service.UserServiceModel;
import service.UserService;
import util.ModelMapper;
import util.SecurityValidator;
import util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public RegisterServlet(UserService userService, ModelMapper modelMapper, SecurityValidator securityValidator, ValidationUtil validationUtil) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingModel model = (UserRegisterBindingModel) req.getAttribute("model");
        if (!model.getPassword().equals(model.getConfirmPassword()) ||
                !this.validationUtil.isValid(model)) {
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }
        this.userService.register(this.modelMapper.map(model, UserServiceModel.class));
        resp.sendRedirect("/login");
    }
}
