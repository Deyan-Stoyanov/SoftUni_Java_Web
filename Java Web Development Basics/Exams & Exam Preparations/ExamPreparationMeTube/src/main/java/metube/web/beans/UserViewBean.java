package metube.web.beans;

import metube.domain.model.view.UserViewModel;
import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class UserViewBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private UserViewModel userViewModel;
    private int index;

    public UserViewBean() {
    }

    @Inject
    public UserViewBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");
        this.userViewModel = this.modelMapper.map(this.userService.findByUsername(username), UserViewModel.class);
        this.index = 1;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    public int getIndex() {
        return index++;
    }
}
