package domain.model.binding;

import domain.enums.TubeStatus;

import javax.validation.constraints.Pattern;

public class TubeUploadBindingModel {
    private String title;
    private String author;
    private String youtubeId;
    private String description;
    private String uploader;
    private TubeStatus status;

    public TubeUploadBindingModel() {
    }

    public TubeUploadBindingModel(String title, String author, String youtubeId, String description, String uploader, TubeStatus status) {
        this.title = title;
        this.author = author;
        this.youtubeId = youtubeId;
        this.description = description;
        this.uploader = uploader;
        this.status = status;
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

    @Pattern(regexp = "https:\\/\\/www\\.youtube\\.com\\/watch\\?v=[a-zA-Z0-9_\\-]{11}")
    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }
}
