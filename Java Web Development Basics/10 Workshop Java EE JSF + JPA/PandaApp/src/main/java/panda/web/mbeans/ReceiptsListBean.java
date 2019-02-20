package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.view.ReceiptViewModel;
import panda.service.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptsListBean {
    private ReceiptService receiptService;
    private ModelMapper modelMapper;
    private List<ReceiptViewModel> receipts;

    public ReceiptsListBean() {
    }

    @Inject
    public ReceiptsListBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");
        this.receipts = this.receiptService.getAll()
                .stream()
                .filter(r -> r.getRecipient().getUsername().equals(username))
                .map(r -> this.modelMapper.map(r, ReceiptViewModel.class))
                .collect(Collectors.toList());
    }

    public List<ReceiptViewModel> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptViewModel> receipts) {
        this.receipts = receipts;
    }
}
