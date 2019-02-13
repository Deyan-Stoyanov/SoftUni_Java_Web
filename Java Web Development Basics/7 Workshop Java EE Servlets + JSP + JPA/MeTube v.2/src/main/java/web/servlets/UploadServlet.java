package web.servlets;

import domain.enums.TubeStatus;
import domain.model.binding.TubeUploadBindingModel;
import domain.model.service.TubeServiceModel;
import domain.model.service.UserServiceModel;
import service.TubeService;
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

@WebServlet("/upload-tube")
public class UploadServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public UploadServlet(TubeService tubeService, ModelMapper modelMapper, SecurityValidator securityValidator, ValidationUtil validationUtil) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeUploadBindingModel model = (TubeUploadBindingModel) req.getAttribute("model");
        TubeServiceModel tubeServiceModel = this.modelMapper.map(model, TubeServiceModel.class);
        UserServiceModel userServiceModel = new UserServiceModel(model.getUploader());
        tubeServiceModel.setUploader(userServiceModel);
        tubeServiceModel.setStatus(TubeStatus.PENDING);
        if(!this.validationUtil.isValid(model)) {
            req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
            return;
        }
        this.tubeService.upload(tubeServiceModel);
        resp.sendRedirect("/home");
    }
}
