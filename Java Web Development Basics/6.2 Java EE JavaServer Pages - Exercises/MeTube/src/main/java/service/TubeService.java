package service;

import domain.models.service.TubeServiceModel;
import domain.models.view.TubeAllViewModel;
import domain.models.view.TubeDetailsViewModel;

import java.util.List;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);

    TubeDetailsViewModel findTubeByName(String name);

    List<TubeAllViewModel> findAllTubes();
}
