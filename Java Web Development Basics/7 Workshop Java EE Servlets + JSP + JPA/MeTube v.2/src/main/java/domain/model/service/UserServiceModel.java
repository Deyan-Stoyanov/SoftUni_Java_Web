package domain.model.service;

import domain.entity.Tube;
import domain.enums.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private List<TubeServiceModel> tubes;
    private Role role;

    public UserServiceModel() {
        this.tubes = new ArrayList<>();
    }

    public UserServiceModel(String username) {
        this.username = username;
        this.tubes = new ArrayList<>();
    }

    public UserServiceModel(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tubes = new ArrayList<>();
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

    public List<TubeServiceModel> getTubes() {
        return Collections.unmodifiableList(this.tubes);
    }

    public void setTubes(List<TubeServiceModel> tubes) {
        this.tubes = tubes;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
