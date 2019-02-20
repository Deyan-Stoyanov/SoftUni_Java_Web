package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entity.Package;
import panda.domain.enums.Status;
import panda.domain.model.service.PackageServiceModel;
import panda.repository.PackageRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(PackageServiceModel packageServiceModel) {
        this.packageRepository.save(this.modelMapper.map(packageServiceModel, Package.class));
    }

    @Override
    public List<PackageServiceModel> getAll() {
        return this.packageRepository.getAll()
                .stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void register(PackageServiceModel packageServiceModel) {
        packageServiceModel.setStatus(Status.PENDING);
        packageServiceModel.setRecipient(packageServiceModel.getRecipient());
        this.packageRepository.update(this.modelMapper.map(packageServiceModel, Package.class));
    }

    @Override
    public List<PackageServiceModel> getListOfPackagesByStatus(Status status) {
        return this.packageRepository.getAll()
                .stream()
                .filter(p -> p.getStatus().equals(status))
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageServiceModel getById(String id) {
        return this.modelMapper.map(this.packageRepository.getById(id), PackageServiceModel.class);
    }

    @Override
    public void changeStatus(String id, Status status) {
        this.packageRepository.updateStatus(id, status);
    }

    @Override
    public void setDeliveryDate(String id) {
        Random random = new Random();
        int result = random.nextInt(40 - 20) + 20;
        LocalDateTime date = LocalDateTime.now().plusDays(result);
        this.packageRepository.updateDeliveryDate(id, date);
    }
}
