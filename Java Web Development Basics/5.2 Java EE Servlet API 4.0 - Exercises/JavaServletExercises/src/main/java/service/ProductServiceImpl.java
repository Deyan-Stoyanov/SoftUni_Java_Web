package service;

import domain.entities.Product;
import domain.enums.Type;
import domain.models.service.ProductServiceModel;
import domain.models.view.AllProductsViewModel;
import domain.models.view.ProductViewModel;
import repository.ProductRepository;
import util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll().stream()
                .map(product -> {
                    ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
                    productServiceModel.setType(product.getType().name());
                    return productServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AllProductsViewModel> allProducts() {
        return this.findAllProducts().stream().map(product -> new AllProductsViewModel(product.getName())).collect(Collectors.toList());
    }

    @Override
    public ProductViewModel findProductByName(String name) {
        Product product = this.productRepository.findByName(name);
        ProductViewModel productViewModel = this.modelMapper.map(this.productRepository.findByName(name), ProductViewModel.class);
        productViewModel.setType(product.getType().name());
        return productViewModel;
    }
}
