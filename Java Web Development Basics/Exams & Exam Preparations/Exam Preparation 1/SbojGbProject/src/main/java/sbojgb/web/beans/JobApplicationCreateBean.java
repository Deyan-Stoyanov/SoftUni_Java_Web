package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.model.binding.JobApplicationCreateBindingModel;
import sbojgb.domain.model.service.JobApplicationServiceModel;
import sbojgb.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobApplicationCreateBean {
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private JobApplicationCreateBindingModel jobApplicationCreateBindingModel;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.jobApplicationCreateBindingModel = new JobApplicationCreateBindingModel();
    }

    public void register() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.jobApplicationService
                .save(this.modelMapper.map(this.jobApplicationCreateBindingModel, JobApplicationServiceModel.class));
        context.redirect("/home");
    }

    public JobApplicationCreateBindingModel getJobApplicationCreateBindingModel() {
        return jobApplicationCreateBindingModel;
    }

    public void setJobApplicationCreateBindingModel(JobApplicationCreateBindingModel jobApplicationCreateBindingModel) {
        this.jobApplicationCreateBindingModel = jobApplicationCreateBindingModel;
    }
}
