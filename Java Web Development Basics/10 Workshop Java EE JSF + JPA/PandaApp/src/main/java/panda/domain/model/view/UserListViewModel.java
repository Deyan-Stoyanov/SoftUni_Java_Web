package panda.domain.model.view;

public class UserListViewModel {
    private String username;

    public UserListViewModel() {
    }

    public UserListViewModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
