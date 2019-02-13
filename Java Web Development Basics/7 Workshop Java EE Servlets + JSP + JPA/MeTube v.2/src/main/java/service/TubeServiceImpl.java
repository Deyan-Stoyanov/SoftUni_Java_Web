package service;

import domain.entity.Tube;
import domain.enums.TubeStatus;
import domain.model.service.TubeServiceModel;
import domain.model.service.UserServiceModel;
import repository.TubeRepository;
import util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserService userService, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void upload(TubeServiceModel tubeServiceModel) {
        UserServiceModel uploader = this.userService.findByUsername(tubeServiceModel.getUploader().getUsername());
        tubeServiceModel.setUploader(uploader);
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.update(tube);
    }

    @Override
    public TubeServiceModel findById(String id) {
        return this.modelMapper.map(this.tubeRepository.getById(id), TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> findByUploaderId(String uploaderId) {
        return this.tubeRepository.getByUploaderId(uploaderId).stream()
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TubeServiceModel> findAll() {
        return this.tubeRepository.getAll().stream()
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void incrementViews(String tubeId) {
        this.tubeRepository.incrementViews(tubeId);
    }

    @Override
    public List<TubeServiceModel> findByStatus(TubeStatus status) {
        return this.tubeRepository.getAll()
                .stream()
                .filter(tube -> tube.getStatus().equals(status))
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatus(String id, TubeStatus status){
        this.tubeRepository.updateStatus(id, status);
    }
}
