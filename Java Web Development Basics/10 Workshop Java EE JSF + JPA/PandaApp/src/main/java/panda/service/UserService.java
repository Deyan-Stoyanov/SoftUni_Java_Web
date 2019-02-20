package panda.service;

import panda.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void save(UserServiceModel userServiceModel);

    UserServiceModel getByUsername(String username);

    List<UserServiceModel> getAll();

    boolean login(String username, String password);

    void register(UserServiceModel map);

    boolean isAdmin(UserServiceModel model);
}
