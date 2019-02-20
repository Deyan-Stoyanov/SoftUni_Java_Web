package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.view.UserListViewModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserListBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private List<UserListViewModel> userListViewModels;

    public UserListBean() {
        this.userListViewModels = this.initUsers();
    }

    @Inject
    public UserListBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userListViewModels = this.initUsers();
    }

    private List<UserListViewModel> initUsers() {
        return this.userService
                .getAll().stream()
                .map(user -> this.modelMapper.map(user, UserListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserListViewModel> getUserListViewModels() {
        return this.userListViewModels;
    }
}
