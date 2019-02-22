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
import javax.servlet.http.HttpSession;
import java.util.List;

@Named
@RequestScoped
public class UserFriendsListBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private List<UserListViewModel> userListViewModels;

    public UserFriendsListBean() {
    }

    @Inject
    public UserFriendsListBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");
        UserListViewModel currentUser = this.modelMapper
                .map(this.userService.findByUsername(username), UserListViewModel.class);
        this.userListViewModels = currentUser.getFriends();
    }

    public List<UserListViewModel> getUserListViewModels() {
        return userListViewModels;
    }

    public void setUserListViewModels(List<UserListViewModel> userListViewModels) {
        this.userListViewModels = userListViewModels;
    }
}
