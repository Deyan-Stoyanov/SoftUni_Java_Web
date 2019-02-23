package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.model.view.JobApplicationViewModel;
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
public class JobApplicationDeleteBean {
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private JobApplicationViewModel jobApplicationViewModel;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");
        this.jobApplicationViewModel = this.modelMapper
                .map(this.jobApplicationService.findById(id), JobApplicationViewModel.class);
    }

    public void delete(String id) throws IOException {
        this.jobApplicationService.delete(id);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/home");
    }

    public JobApplicationViewModel getJobApplicationViewModel() {
        return jobApplicationViewModel;
    }

    public void setJobApplicationViewModel(JobApplicationViewModel jobApplicationViewModel) {
        this.jobApplicationViewModel = jobApplicationViewModel;
    }
}
