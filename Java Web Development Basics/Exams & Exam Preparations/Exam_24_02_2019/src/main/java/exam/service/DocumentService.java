package exam.service;

import exam.domain.model.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    void save(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findById(String id);

    DocumentServiceModel findByTitle(String title);

    List<DocumentServiceModel> findAll();

    void delete(DocumentServiceModel documentServiceModel);
}
