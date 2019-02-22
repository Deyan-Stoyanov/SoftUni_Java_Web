package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserListBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private List<UserListViewModel> userListViewModels;

    public UserListBean() {
    }

    @Inject
    public UserListBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.userListViewModels = this.userService
                .findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserListViewModel> getUserListViewModels() {
        return userListViewModels;
    }

    public void setUserListViewModels(List<UserListViewModel> userListViewModels) {
        this.userListViewModels = userListViewModels;
    }

    private String getCurrentUserUsername() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        return (String) session.getAttribute("username");
    }

    public void addFriend(String id) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel model = this.userService.findByUsername(this.getCurrentUserUsername());
        if (model.getId().equals(id) || model.getFriends().stream().anyMatch(f -> f.getId().equals(id))) {
            context.redirect("/faces/view/home.xhtml");
            return;
        }
        UserServiceModel friend = this.userService.findById(id);
        this.userService.addFriend(model, friend);
        context.redirect("/faces/view/friends.xhtml");
    }

    public void removeFriend(String id) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel model = this.userService.findByUsername(this.getCurrentUserUsername());
        UserServiceModel friend = this.userService.findById(id);
        this.userService.removeFriend(model, friend);
        context.redirect("/faces/view/friends.xhtml");
    }
}
