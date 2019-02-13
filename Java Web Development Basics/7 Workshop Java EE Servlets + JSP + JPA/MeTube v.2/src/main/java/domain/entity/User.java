package domain.entity;

import domain.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private List<Tube> tubes;
    private Role role;

    public User() {
        this.tubes = new ArrayList<>();
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tubes = new ArrayList<>();
        this.role = role;
    }

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 2, max = 25)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_\\-]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Tube.class, mappedBy = "uploader")
    public List<Tube> getTubes() {
        return Collections.unmodifiableList(this.tubes);
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }

    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
