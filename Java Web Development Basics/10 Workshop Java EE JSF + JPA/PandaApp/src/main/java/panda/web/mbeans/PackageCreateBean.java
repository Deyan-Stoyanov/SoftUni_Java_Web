package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.binding.PackageCreateBindingModel;
import panda.domain.model.service.PackageServiceModel;
import panda.service.PackageService;
import panda.service.UserService;
import panda.util.ValidationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class PackageCreateBean {
    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private PackageCreateBindingModel model;

    public PackageCreateBean() {
        this.model = new PackageCreateBindingModel();
    }

    @Inject
    public PackageCreateBean(PackageService packageService, UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    public PackageCreateBindingModel getModel() {
        return model;
    }

    public void setModel(PackageCreateBindingModel model) {
        this.model = model;
    }

    public void createPackage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        PackageServiceModel packageServiceModel = this.modelMapper.map(this.model, PackageServiceModel.class);
        packageServiceModel.setRecipient(userService.getByUsername(this.model.getRecipient()));
        this.packageService.register(packageServiceModel);
        context.redirect("/faces/jsf/home.xhtml");
    }
}
