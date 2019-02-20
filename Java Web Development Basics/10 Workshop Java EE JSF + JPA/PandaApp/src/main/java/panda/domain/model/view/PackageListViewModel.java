package panda.domain.model.view;

public class PackageListViewModel {
    private String id;
    private String description;
    private UserListViewModel recipient;

    public PackageListViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserListViewModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserListViewModel recipient) {
        this.recipient = recipient;
    }
}
