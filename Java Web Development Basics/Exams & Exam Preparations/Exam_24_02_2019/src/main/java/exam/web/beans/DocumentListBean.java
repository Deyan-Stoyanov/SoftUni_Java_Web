package exam.web.beans;

import exam.domain.model.view.DocumentListViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DocumentListBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;
    private List<DocumentListViewModel> documentListViewModels;

    public DocumentListBean() {
    }

    @Inject
    public DocumentListBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.documentListViewModels = this.documentService
                .findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DocumentListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<DocumentListViewModel> getDocumentListViewModels() {
        return documentListViewModels;
    }

    public void setDocumentListViewModels(List<DocumentListViewModel> documentListViewModels) {
        this.documentListViewModels = documentListViewModels;
    }
}
