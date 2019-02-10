package service;

import domain.models.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    boolean save(EmployeeServiceModel employeeServiceModel);
    List<EmployeeServiceModel> findAllEmployees();

    void delete(String id);
}
