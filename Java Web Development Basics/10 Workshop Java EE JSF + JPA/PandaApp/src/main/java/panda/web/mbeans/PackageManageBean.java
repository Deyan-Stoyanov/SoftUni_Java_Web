package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Status;
import panda.domain.model.binding.ReceiptCreateBindingModel;
import panda.domain.model.service.PackageServiceModel;
import panda.domain.model.service.ReceiptServiceModel;
import panda.service.PackageService;
import panda.service.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class PackageManageBean {
    private PackageService packageService;
    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public PackageManageBean() {
    }

    @Inject
    public PackageManageBean(PackageService packageService, ReceiptService receiptService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    private void changeStatus(String id, Status status) {
        this.packageService.changeStatus(id, status);
    }

    public void ship(String id) throws IOException {
        this.packageService.setDeliveryDate(id);
        this.changeStatus(id, Status.SHIPPED);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/packages/shipped");
    }

    public void deliver(String id) throws IOException {
        this.changeStatus(id, Status.DELIVERED);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/packages/delivered");
    }

    public void acquire(String id) throws IOException {
        this.changeStatus(id, Status.ACQUIRED);
        this.createReceipt(id);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/home");
    }

    private void createReceipt(String id) {
        PackageServiceModel packageServiceModel = this.packageService.getById(id);
        ReceiptCreateBindingModel receiptCreateBindingModel = new ReceiptCreateBindingModel(
                BigDecimal.valueOf(packageServiceModel.getWeight() * 2.67),
                LocalDateTime.now(),
                packageServiceModel.getRecipient(),
                packageServiceModel
        );
        this.receiptService.save(this.modelMapper.map(receiptCreateBindingModel, ReceiptServiceModel.class));
    }
}
