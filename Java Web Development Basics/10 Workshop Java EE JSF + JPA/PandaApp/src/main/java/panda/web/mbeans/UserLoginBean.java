package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.binding.UserLoginBindingModel;
import panda.domain.model.service.UserServiceModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private UserLoginBindingModel userLoginBindingModel;

    public UserLoginBean() {
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void userLogin() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(!this.userService.login(this.userLoginBindingModel.getUsername(), this.userLoginBindingModel.getPassword())){
            context.redirect("/faces/jsf/login.xhtml");
            return;
        }
        HttpSession session = (HttpSession) context.getSession(false);
        boolean isAdmin = this.userService.isAdmin(this.modelMapper.map(this.getUserLoginBindingModel(), UserServiceModel.class));
        session.setAttribute("username", this.getUserLoginBindingModel().getUsername());
        session.setAttribute("admin", isAdmin);
        context.redirect("/faces/jsf/home.xhtml");
    }
}

