package metube.web.beans;

import metube.domain.model.view.TubeViewModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TubeDetailsBean {
    private TubeService tubeService;
    private ModelMapper modelMapper;
    private TubeViewModel tubeViewModel;

    public TubeDetailsBean() {
    }

    @Inject
    public TubeDetailsBean(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");
        this.tubeViewModel = this.modelMapper.map(this.tubeService.findById(id), TubeViewModel.class);
    }

    public TubeViewModel getTubeViewModel() {
        return tubeViewModel;
    }

    public void setTubeViewModel(TubeViewModel tubeViewModel) {
        this.tubeViewModel = tubeViewModel;
    }
}
