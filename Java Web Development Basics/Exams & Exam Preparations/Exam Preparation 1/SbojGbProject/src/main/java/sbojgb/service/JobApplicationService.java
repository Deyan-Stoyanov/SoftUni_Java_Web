package sbojgb.service;

import sbojgb.domain.model.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    void save(JobApplicationServiceModel jobApplicationServiceModel);

    JobApplicationServiceModel findById(String id);

    List<JobApplicationServiceModel> findAll();

    void delete(String id);
}
