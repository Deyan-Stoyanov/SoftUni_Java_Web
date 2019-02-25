package exam.web.beans;

import exam.domain.model.view.DocumentDetailsViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;
    private DocumentDetailsViewModel documentDetailsViewModel;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        String id = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        this.documentDetailsViewModel = this.modelMapper
                .map(this.documentService.findById(id), DocumentDetailsViewModel.class);
    }

    public DocumentDetailsViewModel getDocumentDetailsViewModel() {
        return documentDetailsViewModel;
    }

    public void setDocumentDetailsViewModel(DocumentDetailsViewModel documentDetailsViewModel) {
        this.documentDetailsViewModel = documentDetailsViewModel;
    }
}
