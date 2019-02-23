package metube.web.beans;

import metube.domain.model.view.TubeViewModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TubeListViewBean {
    private TubeService tubeService;
    private ModelMapper modelMapper;
    private List<TubeViewModel> tubes;

    public TubeListViewBean() {
    }

    @Inject
    public TubeListViewBean(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.tubes = this.tubeService.findAll()
                .stream()
                .map(tubeServiceModel -> this.modelMapper.map(tubeServiceModel, TubeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<TubeViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubeViewModel> tubes) {
        this.tubes = tubes;
    }
}
