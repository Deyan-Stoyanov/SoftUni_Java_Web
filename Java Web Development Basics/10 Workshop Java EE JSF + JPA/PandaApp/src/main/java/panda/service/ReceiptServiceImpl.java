package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entity.Receipt;
import panda.domain.model.service.ReceiptServiceModel;
import panda.repository.ReceiptRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(ReceiptServiceModel receiptServiceModel) {
        this.receiptRepository.save(this.modelMapper.map(receiptServiceModel, Receipt.class));
    }

    @Override
    public List<ReceiptServiceModel> getAll() {
        return this.receiptRepository.getAll()
                .stream()
                .map(receipt -> this.modelMapper.map(receipt, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptServiceModel getById(String id) {
        return this.modelMapper.map(this.receiptRepository.getById(id), ReceiptServiceModel.class);
    }
}
