package exam.web.beans;

import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import exam.domain.model.service.DocumentServiceModel;
import exam.domain.model.view.DocumentDetailsViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Named
@RequestScoped
public class DocumentPrintBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;
    private DocumentDetailsViewModel documentDetailsViewModel;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");
        this.documentDetailsViewModel = this.modelMapper
                .map(this.documentService.findById(id), DocumentDetailsViewModel.class);
    }

    public DocumentDetailsViewModel getDocumentDetailsViewModel() {
        return documentDetailsViewModel;
    }

    public void setDocumentDetailsViewModel(DocumentDetailsViewModel documentDetailsViewModel) {
        this.documentDetailsViewModel = documentDetailsViewModel;
    }

    public void print() throws IOException {
        this.documentService
                .delete(this.modelMapper.map(this.documentDetailsViewModel, DocumentServiceModel.class));
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            this.parseDocument(context);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        context.redirect("/home");
    }

    //Parsing PDF document
    private void parseDocument(ExternalContext context) throws IOException, DocumentException {
        String formattedDocument = this
                .formatDocument(this.documentDetailsViewModel.getTitle(), this.documentDetailsViewModel.getContent());

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(formattedDocument));
        document.close();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setHeader("Content-disposition", "attachment;");
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    private String formatDocument(String title, String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>").append(title).append("</h1>");
        String[] allLinesOfContent = content.split("\\r?\\n");
        for (String line : allLinesOfContent) {
            if (line.startsWith("#")) {
                int count = line.substring(0, line.lastIndexOf("#") + 1).length();
                sb.append(String.format("<h%d>%s</h%d>", count, line.substring(count), count));
            } else if (line.matches("\\*[^*]+")) {
                sb.append(String.format("<ul><li>%s</li></ul>", line.substring(1)));
            } else {
                sb.append("<p>").append(line).append("</p>");
            }
        }
        String formattedString = sb.toString();
        while (formattedString.contains("**")) {
            int firstIndex = formattedString.indexOf("**");
            int secondIndex = formattedString.indexOf("**", firstIndex + 2) + 2;
            if (secondIndex != 1) {
                String word = formattedString.substring(firstIndex, secondIndex);
                String newWord = word.replaceFirst("\\*\\*", "<b>");
                newWord = newWord.replace("**", "</b>");
                formattedString = formattedString.replace(word, newWord);
            } else {
                formattedString = formattedString.replaceAll("\\*\\*", "");
            }
        }
        return formattedString;
    }
}
