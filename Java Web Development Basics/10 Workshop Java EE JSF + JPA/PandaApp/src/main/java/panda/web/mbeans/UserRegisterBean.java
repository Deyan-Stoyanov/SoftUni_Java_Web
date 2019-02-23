package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.binding.UserRegisterBindingModel;
import panda.domain.model.service.UserServiceModel;
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
public class UserRegisterBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private UserRegisterBindingModel userRegisterBindingModel;

    public UserRegisterBean() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void registerUser() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (!this.validationUtil.validate(this.userRegisterBindingModel) ||
                !this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            context.redirect("/register");
        }
        this.userService.register(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        context.redirect("/login");
    }
}
