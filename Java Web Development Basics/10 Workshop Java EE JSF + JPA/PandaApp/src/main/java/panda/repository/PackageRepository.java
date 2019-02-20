package panda.repository;

import panda.domain.entity.Package;
import panda.domain.enums.Status;

import java.time.LocalDateTime;

public interface PackageRepository extends GenericRepository<Package, String> {

    Package update(Package aPackage);

    void updateStatus(String id, Status status);

    void updateDeliveryDate(String id, LocalDateTime date);
}

