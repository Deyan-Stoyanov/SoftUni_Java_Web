package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.view.PackageDetailsViewModel;
import panda.service.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PackageDetailsBean {
    private PackageService packageService;
    private ModelMapper modelMapper;
    private PackageDetailsViewModel packageDetailsViewModel;

    public PackageDetailsBean() {
    }

    @Inject
    public PackageDetailsBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
     }

     @PostConstruct
     private void init(){
         this.packageDetailsViewModel = this.modelMapper.map(this.packageService.getById(this.retrieveId()), PackageDetailsViewModel.class);
     }

    public PackageDetailsViewModel getPackageDetailsViewModel() {
        return packageDetailsViewModel;
    }

    public void setPackageDetailsViewModel(PackageDetailsViewModel packageDetailsViewModel) {
        this.packageDetailsViewModel = packageDetailsViewModel;
    }

    private String retrieveId() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return context.getRequestParameterMap().get("id");
    }
}

