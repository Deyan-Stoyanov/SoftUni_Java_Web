package exam.repository;

import exam.domain.entity.Document;

public interface DocumentRepository extends GenericRepository<Document, String> {
    Document findByTitle(String title);

    void delete(String id);
}
