package metube.repository;

import metube.domain.entity.User;

public interface UserRepository extends GenericRepository<User,String> {
    User getByUsername(String username);
}
