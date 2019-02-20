package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.model.service.ReceiptServiceModel;
import panda.domain.model.view.PackageDetailsViewModel;
import panda.domain.model.view.ReceiptViewModel;
import panda.service.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ReceiptDetailsBean {
    private ReceiptService receiptService;
    private ModelMapper modelMapper;
    private ReceiptViewModel receipt;

    public ReceiptDetailsBean() {
    }

    @Inject
    public ReceiptDetailsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");
        ReceiptServiceModel receiptServiceModel = this.receiptService.getById(id);
        ReceiptViewModel receiptViewModel = this.modelMapper.map(receiptServiceModel, ReceiptViewModel.class);
        receiptViewModel.setReceiptPackage(this.modelMapper.map(receiptServiceModel.getPackage(), PackageDetailsViewModel.class));
        this.receipt = receiptViewModel;
    }

    public ReceiptViewModel getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptViewModel receipt) {
        this.receipt = receipt;
    }
}
