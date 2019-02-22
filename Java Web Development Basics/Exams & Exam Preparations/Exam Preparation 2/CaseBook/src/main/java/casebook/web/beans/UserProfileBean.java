package casebook.web.beans;

import casebook.domain.models.view.UserListViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserProfileBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private UserListViewModel userViewModel;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("username");
        this.userViewModel = this.modelMapper.map(this.userService.findByUsername(id), UserListViewModel.class);
    }

    public UserListViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserListViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }
}
