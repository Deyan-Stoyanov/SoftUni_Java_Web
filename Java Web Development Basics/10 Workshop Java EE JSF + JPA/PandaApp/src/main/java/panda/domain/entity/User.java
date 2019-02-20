package panda.domain.entity;

import panda.domain.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private Role role;
    private List<Package> packages;
    private List<Receipt> receipts;

    public User() {
        this.packages = new ArrayList<>();
        this.receipts = new ArrayList<>();
    }

    public User(String username, String password, String email, Role role) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "role", updatable = false)
    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(targetEntity = Package.class, mappedBy = "recipient")
    public List<Package> getPackages() {
        return this.packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    @OneToMany(targetEntity = Receipt.class, mappedBy = "recipient")
    public List<Receipt> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
