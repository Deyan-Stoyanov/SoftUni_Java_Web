package domain.model.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserProfileViewModel {
    private String username;
    private String email;
    private List<TubeProfileViewModel> tubes;

    public UserProfileViewModel() {
        this.tubes = new ArrayList<>();
    }

    public UserProfileViewModel(String username, String email) {
        this.username = username;
        this.email = email;
        this.tubes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TubeProfileViewModel> getTubes() {
        return Collections.unmodifiableList(this.tubes);
    }

    public void setTubes(List<TubeProfileViewModel> tubes) {
        this.tubes = tubes;
    }
}
