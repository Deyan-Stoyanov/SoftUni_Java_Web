package metube.service;

import metube.domain.entity.Tube;
import metube.domain.model.service.TubeServiceModel;
import metube.repository.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(TubeServiceModel tubeServiceModel) {
        this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public void update(TubeServiceModel tubeServiceModel) {
        this.tubeRepository.update(this.modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public TubeServiceModel findById(String id) {
        try {
            return this.modelMapper.map(this.tubeRepository.getById(id), TubeServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public TubeServiceModel findByName(String name) {
        try {
            return this.modelMapper.map(this.tubeRepository.getByName(name), TubeServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<TubeServiceModel> findAll() {
        return this.tubeRepository.getAll()
                .stream()
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());
    }
}
