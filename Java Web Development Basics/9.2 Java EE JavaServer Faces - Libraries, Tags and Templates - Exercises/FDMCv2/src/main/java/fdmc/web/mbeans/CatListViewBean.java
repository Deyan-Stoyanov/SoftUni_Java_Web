package fdmc.web.mbeans;

import fdmc.domain.model.view.CatViewModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("cats")
@RequestScoped
public class CatListViewBean {
    private CatService catService;
    private ModelMapper modelMapper;
    private List<CatViewModel> allCats;

    public CatListViewBean() {
        this.allCats = this.catService.findAll()
                .stream()
                .map(cat -> this.modelMapper.map(cat, CatViewModel.class))
                .collect(Collectors.toList());
    }

    @Inject
    public CatListViewBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.allCats = this.catService.findAll()
                .stream()
                .map(cat -> this.modelMapper.map(cat, CatViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatViewModel> getAllCats() {
        return allCats;
    }
}
