package domain.models.view;

public class TubeAllViewModel {
    private String name;

    public TubeAllViewModel() {
    }

    public TubeAllViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
