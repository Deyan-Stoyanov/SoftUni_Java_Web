package web.mbeans;

import domain.models.binding.EmployeeBindingModel;
import domain.models.service.EmployeeServiceModel;
import org.modelmapper.ModelMapper;
import service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {
    private EmployeeBindingModel employeeBindingModel;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
        this.employeeBindingModel = new EmployeeBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public EmployeeBindingModel getEmployeeBindingModel() {
        return employeeBindingModel;
    }

    public void setEmployeeBindingModel(EmployeeBindingModel employeeBindingModel) {
        this.employeeBindingModel = employeeBindingModel;
    }

    public void register() throws IOException {
        this.employeeService
                .save(this.modelMapper.map(this.employeeBindingModel, EmployeeServiceModel.class));
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
