package fdmc.repository;

import fdmc.domain.entity.Cat;

public interface CatRepository extends GenericRepository<Cat, String> {
    Cat getByName(String name);
}
