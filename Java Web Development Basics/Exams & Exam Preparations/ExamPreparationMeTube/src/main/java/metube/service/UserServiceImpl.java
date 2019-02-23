package metube.service;

import metube.domain.entity.User;
import metube.domain.model.service.UserServiceModel;
import metube.repository.UserRepository;
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
    public void update(UserServiceModel userServiceModel){
        this.userRepository.update(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public boolean login(String username, String password) {
        UserServiceModel userServiceModel = this.findByUsername(username);
        return userServiceModel != null &&
                userServiceModel.getPassword().equals(DigestUtils.sha256Hex(password));
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        this.save(userServiceModel);
    }

    @Override
    public UserServiceModel findById(String id) {
        try {
            return this.modelMapper
                    .map(this.userRepository.getById(id), UserServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        try {
            return this.modelMapper
                    .map(this.userRepository.getByUsername(username), UserServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository.getAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }
}
