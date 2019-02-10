package service;

import domain.entities.Employee;
import domain.models.service.EmployeeServiceModel;
import org.modelmapper.ModelMapper;
import repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean save(EmployeeServiceModel employeeServiceModel) {
        try {
            this.employeeRepository.save(this.modelMapper.map(employeeServiceModel, Employee.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {
        return this.employeeRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.employeeRepository.deleteById(id);
    }
}
