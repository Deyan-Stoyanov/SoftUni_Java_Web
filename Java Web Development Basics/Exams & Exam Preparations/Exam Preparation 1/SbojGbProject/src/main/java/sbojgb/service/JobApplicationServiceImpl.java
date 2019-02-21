package sbojgb.service;

import org.modelmapper.ModelMapper;
import sbojgb.domain.entity.JobApplication;
import sbojgb.domain.model.service.JobApplicationServiceModel;
import sbojgb.repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(JobApplicationServiceModel jobApplicationServiceModel) {
        this.jobApplicationRepository
                .save(this.modelMapper.map(jobApplicationServiceModel, JobApplication.class));
    }

    @Override
    public JobApplicationServiceModel findById(String id) {
        return this.modelMapper
                .map(this.jobApplicationRepository.getById(id), JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> findAll() {
        return this.jobApplicationRepository.getAll()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }
}
