package metube.domain.model.service;

public class TubeServiceModel {
    private String id;
    private String name;
    private String author;
    private String description;
    private String youtubeId;
    private UserServiceModel uploader;

    public TubeServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public UserServiceModel getUploader() {
        return uploader;
    }

    public void setUploader(UserServiceModel uploader) {
        this.uploader = uploader;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
