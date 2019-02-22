package casebook.service;


import casebook.domain.entities.User;
import casebook.domain.models.service.UserServiceModel;
import casebook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {
        UserServiceModel model = this.findByUsername(userServiceModel.getUsername());
        return model != null && model.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()));
    }

    @Override
    public boolean register(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        return this.userRepository.save(this.modelMapper.map(userServiceModel, User.class)) != null;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        try {
            return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public UserServiceModel findById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserServiceModel userServiceModel) {
        this.userRepository.update(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public void addFriend(UserServiceModel user, UserServiceModel friend) {
        user.getFriends().add(friend);
        this.updateUser(user);
    }

    @Override
    public void removeFriend(UserServiceModel user, UserServiceModel friend) {
        user.setFriends(user.getFriends()
                .stream()
                .filter(f -> !f.getUsername().equals(friend.getUsername()))
                .collect(Collectors.toList()));
        this.updateUser(user);
    }
}
