package repository;

import domain.entity.User;

public interface UserRepository extends GenericRepository<User,String>{
    User getByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
}
