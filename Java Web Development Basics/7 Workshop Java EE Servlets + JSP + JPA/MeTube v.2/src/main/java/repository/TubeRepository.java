package repository;

import domain.entity.Tube;
import domain.enums.TubeStatus;

import java.util.List;

public interface TubeRepository extends GenericRepository<Tube, String>{
    Tube getByTitle(String name);

    List<Tube> getByUploaderId(String uploaderId);

    Tube update(Tube tube);

    void incrementViews(String id);

    void updateStatus(String id, TubeStatus status);
}
