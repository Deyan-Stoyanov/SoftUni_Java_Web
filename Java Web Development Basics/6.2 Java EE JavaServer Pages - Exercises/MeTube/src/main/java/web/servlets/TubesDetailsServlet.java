package web.servlets;

import domain.models.view.TubeDetailsViewModel;
import service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class TubesDetailsServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public TubesDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");
        TubeDetailsViewModel tubeDetailsViewModel = this.tubeService.findTubeByName(name);
        req.getSession().setAttribute("tube", tubeDetailsViewModel);
        req.getRequestDispatcher("/jsp/tubes-details.jsp").forward(req, resp);
    }
}
