package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.model.view.JobApplicationViewModel;
import sbojgb.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobApplicationListBean {
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private List<JobApplicationViewModel> jobApplicationViewModels;

    public JobApplicationListBean() {

    }

    @Inject
    public JobApplicationListBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.jobApplicationViewModels = this.jobApplicationService
                .findAll()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobApplicationViewModel> getJobApplicationViewModels() {
        return jobApplicationViewModels;
    }

    public void setJobApplicationViewModels(List<JobApplicationViewModel> jobApplicationViewModels) {
        this.jobApplicationViewModels = jobApplicationViewModels;
    }
}
