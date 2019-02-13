package web.servlets;

import domain.model.service.UserServiceModel;
import domain.model.view.UserProfileViewModel;
import service.TubeService;
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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService;
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService, TubeService tubeService, ModelMapper modelMapper, SecurityValidator securityValidator) {
        this.userService = userService;
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceModel user = this.userService.findByUsername((String) req.getSession().getAttribute("username"));
        UserProfileViewModel model = this.modelMapper.map(user, UserProfileViewModel.class);
        req.setAttribute("model", model);
        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
    }
}
