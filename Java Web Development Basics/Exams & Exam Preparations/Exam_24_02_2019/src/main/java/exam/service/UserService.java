package exam.service;

import exam.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void save(UserServiceModel userServiceModel);

    UserServiceModel findById(String id);

    UserServiceModel findByUsername(String username);

    List<UserServiceModel> findAll();

    boolean login(String username, String password);

    void register(UserServiceModel userServiceModel);
}
