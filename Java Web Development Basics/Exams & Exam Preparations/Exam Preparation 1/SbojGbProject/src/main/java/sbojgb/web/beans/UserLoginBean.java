package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.model.binding.UserLoginBindingModel;
import sbojgb.service.UserService;

import javax.annotation.PostConstruct;
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
    private UserLoginBindingModel userLoginBindingModel;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void init(){
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (!this.userService.login(this.userLoginBindingModel.getUsername(), this.userLoginBindingModel.getPassword())) {
            context.redirect("/login");
            return;
        }
        HttpSession session = (HttpSession) context.getSession(false);
        session.setAttribute("username", this.userLoginBindingModel.getUsername());
        context.redirect("/home");
    }
}
