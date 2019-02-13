package web.servlets;

import domain.enums.TubeStatus;
import service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/admin/tube/decline")
public class DeclineServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public DeclineServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeId = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");
        this.tubeService.changeStatus(tubeId, TubeStatus.DECLINED);
        resp.sendRedirect("/admin/tube/pending");
    }
}
