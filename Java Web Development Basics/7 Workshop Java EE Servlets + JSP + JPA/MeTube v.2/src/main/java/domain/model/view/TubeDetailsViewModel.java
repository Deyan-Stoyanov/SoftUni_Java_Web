package domain.model.view;

public class TubeDetailsViewModel {
    private String id;
    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private long views;

    public TubeDetailsViewModel() {
    }

    public TubeDetailsViewModel(String title, String author, String description, String youtubeId) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.youtubeId = youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
