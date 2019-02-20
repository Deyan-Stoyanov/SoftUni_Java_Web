package web.mbeans;

import domain.models.view.EmployeeViewModel;
import org.modelmapper.ModelMapper;
import service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {
    private List<EmployeeViewModel> employees;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;
    private int index;

    public EmployeeListBean() {
        this.index = 0;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeViewModel> employees) {
        this.employees = employees;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNextIndex() {
        this.setIndex(this.getIndex() + 1);
        return this.getIndex();
    }

    public BigDecimal getTotalMoneyNeeded() {
        return this.employees.stream()
                .map(EmployeeViewModel::getSalary)
                .reduce(new BigDecimal(BigInteger.ZERO), BigDecimal::add);
    }

    public BigDecimal getAverageSalary() {
        if(this.employees.isEmpty()){
            return BigDecimal.valueOf(0);
        }
        return this.getTotalMoneyNeeded().divide(new BigDecimal(this.employees.size()), 2, RoundingMode.CEILING);
    }
}
