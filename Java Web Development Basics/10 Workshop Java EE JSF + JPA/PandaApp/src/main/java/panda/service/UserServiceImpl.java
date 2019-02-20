package panda.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import panda.domain.entity.User;
import panda.domain.enums.Role;
import panda.domain.enums.Status;
import panda.domain.model.service.UserServiceModel;
import panda.domain.model.view.PackageListViewModel;
import panda.repository.UserRepository;

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
    public UserServiceModel getByUsername(String username) {
        return this.modelMapper.map(this.userRepository.getByUsername(username), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.getAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean login(String username, String password) {
        try {
            UserServiceModel model = this.modelMapper.map(this.userRepository.getByUsername(username), UserServiceModel.class);
            return model.getPassword().equals(DigestUtils.sha256Hex(password));

        } catch (NoResultException nre) {
            return false;
        }
    }

    @Override
    public void register(UserServiceModel model) {
        if (this.userRepository.getAll().size() == 0) {
            model.setRole(Role.ADMIN);
        } else {
            model.setRole(Role.USER);
        }
        model.setPassword(DigestUtils.sha256Hex(model.getPassword()));
        this.save(model);
    }

    @Override
    public boolean isAdmin(UserServiceModel model) {
        return this.userRepository.getByUsername(model.getUsername()).getRole().equals(Role.ADMIN);
    }
}
