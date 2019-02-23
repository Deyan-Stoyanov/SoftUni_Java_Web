package metube.web.beans;

import metube.domain.model.binding.TubeCreateBindingModel;
import metube.domain.model.service.TubeServiceModel;
import metube.domain.model.service.UserServiceModel;
import metube.service.TubeService;
import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class TubeUploadBean {
    private TubeService tubeService;
    private UserService userService;
    private ModelMapper modelMapper;
    private TubeCreateBindingModel tubeCreateBindingModel;

    public TubeUploadBean() {
    }

    @Inject
    public TubeUploadBean(TubeService tubeService, UserService userService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.tubeCreateBindingModel = new TubeCreateBindingModel();
    }

    public TubeCreateBindingModel getTubeCreateBindingModel() {
        return tubeCreateBindingModel;
    }

    public void setTubeCreateBindingModel(TubeCreateBindingModel tubeCreateBindingModel) {
        this.tubeCreateBindingModel = tubeCreateBindingModel;
    }

    public void upload() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        TubeServiceModel tube = this.modelMapper.map(this.tubeCreateBindingModel, TubeServiceModel.class);
        tube.setUploader(userServiceModel);
        tube.setYoutubeId(tube.getYoutubeId().substring(tube.getYoutubeId().lastIndexOf("=") + 1));
        this.tubeService.update(tube);
        context.redirect("/home");
    }
}
