package fdmc.service;

import fdmc.domain.entity.Cat;
import fdmc.domain.model.service.CatServiceModel;
import fdmc.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CatServiceModel catServiceModel) {
        this.catRepository.save(this.modelMapper.map(catServiceModel, Cat.class));
    }

    @Override
    public CatServiceModel findByName(String name) {
        return this.modelMapper.map(this.catRepository.getByName(name), CatServiceModel.class);
    }

    @Override
    public List<CatServiceModel> findAll() {
        return this.catRepository.getAll()
                .stream()
                .map(cat -> this.modelMapper.map(cat, CatServiceModel.class))
                .collect(Collectors.toList());
    }
}
