package web.mbeans;

import domain.models.binding.EmployeeBindingModel;
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
public class EmployeeDeleteBean {
    private EmployeeBindingModel employeeBindingModel;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeDeleteBean() {
        this.employeeBindingModel = new EmployeeBindingModel();
    }

    @Inject
    public EmployeeDeleteBean(EmployeeService employeeService, ModelMapper modelMapper) {
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

    public void delete(String id) throws IOException {
        this.employeeService.delete(id);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
