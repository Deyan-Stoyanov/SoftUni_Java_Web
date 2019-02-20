package panda.service;

import panda.domain.enums.Status;
import panda.domain.model.service.PackageServiceModel;

import java.util.List;

public interface PackageService {
    void save(PackageServiceModel packageServiceModel);

    List<PackageServiceModel> getAll();

    void register(PackageServiceModel map);

    List<PackageServiceModel> getListOfPackagesByStatus(Status status);

    PackageServiceModel getById(String id);

    void changeStatus(String id, Status status);

    void setDeliveryDate(String id);
}
