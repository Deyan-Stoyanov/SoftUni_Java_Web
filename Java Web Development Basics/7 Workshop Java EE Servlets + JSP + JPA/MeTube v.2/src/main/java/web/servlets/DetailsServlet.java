package web.servlets;

import domain.model.view.TubeDetailsViewModel;
import service.TubeService;
import util.ModelMapper;
import util.SecurityValidator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/admin/tube/details")
public class DetailsServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public DetailsServlet(TubeService tubeService, ModelMapper modelMapper, SecurityValidator securityValidator) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeId = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");
        this.tubeService.incrementViews(tubeId);
        TubeDetailsViewModel tube = this.modelMapper
                .map(this.tubeService.findById(tubeId), TubeDetailsViewModel.class);
        req.getSession().setAttribute("tube", tube);
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }
}
