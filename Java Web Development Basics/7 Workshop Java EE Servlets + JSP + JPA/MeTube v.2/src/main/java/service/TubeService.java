package service;

import domain.enums.TubeStatus;
import domain.model.service.TubeServiceModel;

import java.util.List;

public interface TubeService {
    void upload(TubeServiceModel tubeServiceModel);

    TubeServiceModel findById(String id);

    List<TubeServiceModel> findByUploaderId(String uploaderId);

    List<TubeServiceModel> findAll();

    void incrementViews(String tubeId);

    List<TubeServiceModel> findByStatus(TubeStatus status);

    void changeStatus(String id, TubeStatus status);
}
