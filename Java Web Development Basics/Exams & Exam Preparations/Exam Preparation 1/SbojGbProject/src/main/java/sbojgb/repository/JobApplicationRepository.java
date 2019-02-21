package sbojgb.repository;

import sbojgb.domain.entity.JobApplication;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {
    void delete(String id);
}
