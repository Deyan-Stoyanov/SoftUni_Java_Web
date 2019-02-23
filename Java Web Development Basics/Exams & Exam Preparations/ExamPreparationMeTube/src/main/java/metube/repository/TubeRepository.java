package metube.repository;

import metube.domain.entity.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
    Tube getByName(String name);
}
