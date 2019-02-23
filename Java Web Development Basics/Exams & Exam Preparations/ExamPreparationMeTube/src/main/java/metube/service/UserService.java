package metube.service;

import metube.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void save(UserServiceModel userServiceModel);

    void update(UserServiceModel userServiceModel);

    boolean login(String username, String password);

    void register(UserServiceModel userServiceModel);

    UserServiceModel findById(String id);

    UserServiceModel findByUsername(String username);

    List<UserServiceModel> findAll();
}
