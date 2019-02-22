package casebook.web.beans;

import casebook.domain.enums.Gender;
import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private UserRegisterBindingModel userRegisterBindingModel;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            context.redirect("/faces/view/register.xhtml");
            return;
        }
        this.userRegisterBindingModel.setGender(this.userRegisterBindingModel.getGender().toUpperCase());
        this.userService.register(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        context.redirect("/faces/view/login.xhtml");
    }
}
