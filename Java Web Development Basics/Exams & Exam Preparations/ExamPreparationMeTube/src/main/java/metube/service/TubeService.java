package metube.service;

import metube.domain.model.service.TubeServiceModel;

import java.util.List;

public interface TubeService {
    void save(TubeServiceModel tubeServiceModel);

    void update(TubeServiceModel tubeServiceModel);

    TubeServiceModel findById(String id);

    TubeServiceModel findByName(String name);

    List<TubeServiceModel> findAll();
}
