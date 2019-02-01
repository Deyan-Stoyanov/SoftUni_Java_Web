package service;

import domain.models.service.ProductServiceModel;
import domain.models.view.AllProductsViewModel;
import domain.models.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    List<AllProductsViewModel> allProducts();

    ProductViewModel findProductByName(String name);
}
