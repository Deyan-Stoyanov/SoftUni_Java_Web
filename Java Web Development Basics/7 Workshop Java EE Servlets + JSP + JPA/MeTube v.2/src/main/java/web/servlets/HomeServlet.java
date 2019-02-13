package web.servlets;

import domain.enums.TubeStatus;
import domain.model.view.TubeDetailsViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public HomeServlet(TubeService tubeService, UserService userService, ModelMapper modelMapper, SecurityValidator securityValidator) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeDetailsViewModel> tubes = this.tubeService.findByStatus(TubeStatus.APPROVED)
                .stream()
                .map(t -> this.modelMapper.map(t, TubeDetailsViewModel.class))
                .collect(Collectors.toList());
        req.getSession().setAttribute("tubes", tubes);
        req.getRequestDispatcher("jsp/home.jsp").forward(req, resp);
    }
}
