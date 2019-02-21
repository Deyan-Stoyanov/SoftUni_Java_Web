package sbojgb.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import sbojgb.domain.entity.User;
import sbojgb.domain.model.service.UserServiceModel;
import sbojgb.repository.UserRepository;

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
        return this.modelMapper.map(this.userRepository.getById(id), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        try {
            return this.modelMapper.map(this.userRepository.getByUsername(username), UserServiceModel.class);
        } catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository.getAll()
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
