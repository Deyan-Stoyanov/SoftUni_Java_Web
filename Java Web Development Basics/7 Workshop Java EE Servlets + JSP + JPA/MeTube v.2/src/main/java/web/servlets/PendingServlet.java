package web.servlets;

import domain.enums.TubeStatus;
import domain.model.view.TubeDetailsViewModel;
import service.TubeService;
import util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/tube/pending")
public class PendingServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public PendingServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeDetailsViewModel> tubes = this.tubeService.findByStatus(TubeStatus.PENDING)
                .stream()
                .map(t -> this.modelMapper.map(t, TubeDetailsViewModel.class))
                .collect(Collectors.toList());
        req.getSession().setAttribute("tubes", tubes);
        req.getRequestDispatcher("/jsp/pending.jsp").forward(req, resp);
    }
}
