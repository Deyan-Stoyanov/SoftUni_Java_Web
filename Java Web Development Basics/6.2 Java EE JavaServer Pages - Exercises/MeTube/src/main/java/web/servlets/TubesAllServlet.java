package web.servlets;

import service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/all")
public class TubesAllServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public TubesAllServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("allTubes", this.tubeService.findAllTubes());
        req.getRequestDispatcher("/jsp/tubes-all.jsp").forward(req, resp);
    }
}
