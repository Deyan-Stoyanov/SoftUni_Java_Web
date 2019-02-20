package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Status;
import panda.domain.model.view.PackageListViewModel;
import panda.service.PackageService;

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
public class ListPackagesBean {
    private PackageService packageService;
    private ModelMapper modelMapper;
    private List<PackageListViewModel> pendingPackages;
    private List<PackageListViewModel> shippedPackages;
    private List<PackageListViewModel> deliveredPackages;
    private List<PackageListViewModel> acquiredPackages;

    public ListPackagesBean() {
    }

    @Inject
    public ListPackagesBean(ModelMapper modelMapper, PackageService packageService) {
        this.modelMapper = modelMapper;
        this.packageService = packageService;
    }

    @PostConstruct
    private void initLists() {
        this.pendingPackages = this.getListOfPackageViewModelsByStatus(Status.PENDING);
        this.shippedPackages = this.getListOfPackageViewModelsByStatus(Status.SHIPPED);
        this.deliveredPackages = this.getListOfPackageViewModelsByStatus(Status.DELIVERED);
        this.acquiredPackages = this.getListOfPackageViewModelsByStatus(Status.ACQUIRED);
    }

    private List<PackageListViewModel> getListOfPackageViewModelsByStatus(Status status) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");
        return this.packageService.getListOfPackagesByStatus(status)
                .stream()
                .filter(p -> p.getRecipient().getUsername().equals(username))
                .map(p -> this.modelMapper.map(p, PackageListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageListViewModel> getPendingPackages() {
        return pendingPackages;
    }

    public List<PackageListViewModel> getShippedPackages() {
        return shippedPackages;
    }

    public List<PackageListViewModel> getDeliveredPackages() {
        return deliveredPackages;
    }

    public List<PackageListViewModel> getAcquiredPackages() {
        return acquiredPackages;
    }
}
