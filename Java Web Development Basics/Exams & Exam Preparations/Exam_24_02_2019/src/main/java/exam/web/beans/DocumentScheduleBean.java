package exam.web.beans;

import exam.domain.model.binding.DocumentCreateBindingModel;
import exam.domain.model.service.DocumentServiceModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentScheduleBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;
    private DocumentCreateBindingModel documentCreateBindingModel;

    public DocumentScheduleBean() {
    }

    @Inject
    public DocumentScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.documentCreateBindingModel = new DocumentCreateBindingModel();
    }

    public DocumentCreateBindingModel getDocumentCreateBindingModel() {
        return documentCreateBindingModel;
    }

    public void setDocumentCreateBindingModel(DocumentCreateBindingModel documentCreateBindingModel) {
        this.documentCreateBindingModel = documentCreateBindingModel;
    }

    public void scheduleDocument() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (this.documentService.findByTitle(this.documentCreateBindingModel.getTitle()) != null) {
            context.redirect("/schedule");
            return;
        }
        this.documentService
                .save(this.modelMapper.map(this.documentCreateBindingModel, DocumentServiceModel.class));
        String id = this.documentService.findByTitle(this.documentCreateBindingModel.getTitle()).getId();
        context.redirect("/documents/details?id=" + id);
    }
}
