package casebook.service;

import casebook.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    boolean login(UserServiceModel userServiceModel);

    boolean register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    List<UserServiceModel> findAll();

    void updateUser(UserServiceModel userServiceModel);

    void addFriend(UserServiceModel user, UserServiceModel friend);

    void removeFriend(UserServiceModel model, UserServiceModel friend);
}
