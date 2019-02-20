package panda.service;

import panda.domain.model.service.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {
    void save(ReceiptServiceModel receiptServiceModel);

    List<ReceiptServiceModel> getAll();

    ReceiptServiceModel getById(String id);
}
