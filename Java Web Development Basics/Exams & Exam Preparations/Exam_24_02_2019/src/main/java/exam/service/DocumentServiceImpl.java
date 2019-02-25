package exam.service;

import exam.domain.entity.Document;
import exam.domain.model.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(DocumentServiceModel documentServiceModel) {
        this.documentRepository.save(this.modelMapper.map(documentServiceModel, Document.class));
    }

    @Override
    public DocumentServiceModel findById(String id) {
        try {
            return this.modelMapper.map(this.documentRepository.findById(id), DocumentServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public DocumentServiceModel findByTitle(String title) {
        try {
            return this.modelMapper.map(this.documentRepository.findByTitle(title), DocumentServiceModel.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        return this.documentRepository
                .findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(DocumentServiceModel documentServiceModel) {
        this.documentRepository.delete(documentServiceModel.getId());
    }
}
