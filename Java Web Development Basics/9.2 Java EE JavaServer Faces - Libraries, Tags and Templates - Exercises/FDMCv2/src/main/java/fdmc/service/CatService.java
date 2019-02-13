package fdmc.service;

import fdmc.domain.model.service.CatServiceModel;

import java.util.List;

public interface CatService {
    void save(CatServiceModel catServiceModel);

    CatServiceModel findByName(String name);

    List<CatServiceModel> findAll();
}
