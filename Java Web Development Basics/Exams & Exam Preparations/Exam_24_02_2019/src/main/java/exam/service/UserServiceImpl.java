package exam.service;

import exam.domain.entity.User;
import exam.domain.model.service.UserServiceModel;
import exam.repository.UserRepository;
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
    public void save(UserServiceModel userServiceModel) {
        this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findById(String id) {
        try {
            return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
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
    public List<UserServiceModel> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean login(String username, String password) {
        UserServiceModel userServiceModel = this.findByUsername(username);
        return userServiceModel != null && userServiceModel.getPassword().equals(DigestUtils.sha256Hex(password));
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        this.save(userServiceModel);
    }
}
