package web.servlets;

import domain.models.service.TubeServiceModel;
import service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public CreateServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeServiceModel tubeServiceModel = new TubeServiceModel(
            req.getParameter("name"),
            req.getParameter("description"),
            req.getParameter("youtubeLink"),
            req.getParameter("uploader")
        );
        this.tubeService.saveTube(tubeServiceModel);
        resp.sendRedirect("/tubes/details?name=" + req.getParameter("name"));
    }
}
