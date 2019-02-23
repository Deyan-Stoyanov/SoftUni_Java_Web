package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.model.binding.UserRegisterBindingModel;
import sbojgb.domain.model.service.UserServiceModel;
import sbojgb.service.UserService;

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
        if (this.userService.findByUsername(this.userRegisterBindingModel.getUsername()) != null ||
                !this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            context.redirect("/register");
            return;
        }
        this.userService.register(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        context.redirect("/login");
    }
}
