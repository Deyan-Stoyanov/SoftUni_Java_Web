package repository;

import domain.entities.Employee;

public interface EmployeeRepository extends GenericRepository<Employee, String> {
    Employee findByFirstName(String firstName);

    void deleteById(String id);
}
