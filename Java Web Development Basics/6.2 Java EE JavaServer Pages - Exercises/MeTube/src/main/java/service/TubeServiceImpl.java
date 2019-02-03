package service;

import domain.entities.Tube;
import domain.models.service.TubeServiceModel;
import domain.models.view.TubeAllViewModel;
import domain.models.view.TubeDetailsViewModel;
import repository.TubeRepository;
import util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository repository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.repository.save(tube);
    }

    @Override
    public TubeDetailsViewModel findTubeByName(String name) {
        return this.modelMapper
                .map(this.repository.findByName(name), TubeDetailsViewModel.class);
    }

    @Override
    public List<TubeAllViewModel> findAllTubes() {
        return this.repository.findAll()
                .stream()
                .map(tube -> this.modelMapper.map(tube, TubeAllViewModel.class))
                .collect(Collectors.toList());
    }
}
