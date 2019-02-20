package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Status;
import panda.domain.model.view.PackageDetailsViewModel;
import panda.service.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageListDetailsBean {
    private PackageService packageService;
    private ModelMapper modelMapper;
    private List<PackageDetailsViewModel> pendingPackages;
    private List<PackageDetailsViewModel> shippedPackages;
    private List<PackageDetailsViewModel> deliveredPackages;

    public PackageListDetailsBean() {

    }

    @Inject
    public PackageListDetailsBean(ModelMapper modelMapper, PackageService packageService) {
        this.modelMapper = modelMapper;
        this.packageService = packageService;
    }

    @PostConstruct
    private void initLists() {
        this.pendingPackages = this.getListOfPackageViewModelsByStatus(Status.PENDING);
        this.shippedPackages = this.getListOfPackageViewModelsByStatus(Status.SHIPPED);
        this.deliveredPackages = this.getListOfPackageViewModelsByStatus(Status.DELIVERED);
        this.deliveredPackages.addAll(this.getListOfPackageViewModelsByStatus(Status.ACQUIRED));
    }

    private List<PackageDetailsViewModel> getListOfPackageViewModelsByStatus(Status status) {
        return this.packageService.getListOfPackagesByStatus(status)
                .stream()
                .map(p -> this.modelMapper.map(p, PackageDetailsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageDetailsViewModel> getPendingPackages() {
        return pendingPackages;
    }

    public List<PackageDetailsViewModel> getShippedPackages() {
        return shippedPackages;
    }

    public List<PackageDetailsViewModel> getDeliveredPackages() {
        return deliveredPackages;
    }
}
