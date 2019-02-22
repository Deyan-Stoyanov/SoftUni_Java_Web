package casebook.domain.models.view;

import casebook.domain.enums.Gender;

import java.util.List;

public class UserListViewModel {
    private String id;
    private String username;
    private String password;
    private Gender gender;
    private List<UserListViewModel> friends;

    public UserListViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<UserListViewModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserListViewModel> friends) {
        this.friends = friends;
    }
}
