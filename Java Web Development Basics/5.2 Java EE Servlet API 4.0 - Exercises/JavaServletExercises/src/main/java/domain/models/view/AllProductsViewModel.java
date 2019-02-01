package domain.models.view;

public class AllProductsViewModel {
    private String name;

    public AllProductsViewModel() {

    }

    public AllProductsViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
