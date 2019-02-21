package sbojgb.repository;

import sbojgb.domain.entity.User;

public interface UserRepository extends GenericRepository<User, String> {
    User getByUsername(String username);
}
