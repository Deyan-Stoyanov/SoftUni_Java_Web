package metube.domain.model.view;

import java.util.List;

public class UserViewModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private List<TubeViewModel> tubes;

    public UserViewModel() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TubeViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubeViewModel> tubes) {
        this.tubes = tubes;
    }
}
