package service;

import domain.entity.User;
import domain.enums.Role;
import domain.model.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import repository.UserRepository;
import util.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.getByUsername(username), UserServiceModel.class);
    }

    @Override
    public boolean register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        if(this.userRepository.getAll().size() == 0){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        try {
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {
        return (this.userRepository.getByUsernameAndPassword(userServiceModel.getUsername(), DigestUtils.sha256Hex(userServiceModel.getPassword())) != null);
    }

    @Override
    public boolean isAdmin(String username){
        return this.userRepository.getByUsername(username).getRole().equals(Role.ADMIN);
    }
}
