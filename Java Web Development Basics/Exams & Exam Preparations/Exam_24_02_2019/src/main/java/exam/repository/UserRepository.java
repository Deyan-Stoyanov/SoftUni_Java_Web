package exam.repository;

import exam.domain.entity.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username);
}
