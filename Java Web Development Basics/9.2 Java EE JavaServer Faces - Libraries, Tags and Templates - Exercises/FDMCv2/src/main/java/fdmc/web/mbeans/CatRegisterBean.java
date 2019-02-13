package fdmc.web.mbeans;

import fdmc.domain.entity.Cat;
import fdmc.domain.model.binding.CatCreateBindingModel;
import fdmc.domain.model.service.CatServiceModel;
import fdmc.service.CatService;
import fdmc.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatRegisterBean {
    private CatService catService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private CatCreateBindingModel catCreateBindingModel;

    public CatRegisterBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatRegisterBean(CatService catService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.modelMapper = modelMapper;
        this.catService = catService;
        this.validationUtil = validationUtil;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void registerCat() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(!this.validationUtil.validate(this.modelMapper.map(this.catCreateBindingModel, Cat.class))){
            context.redirect("jsf/cat-create.xhtml");
        }
        this.catService.save(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        context.redirect("/");
    }
}
