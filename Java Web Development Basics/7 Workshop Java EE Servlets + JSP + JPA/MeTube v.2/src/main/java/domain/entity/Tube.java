package domain.entity;

import domain.enums.TubeStatus;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {
    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private long views;
    private User uploader;
    private TubeStatus status;

    public Tube() {
        this.setStatus(TubeStatus.PENDING);
    }

    public Tube(String title, String author, String description, String youtubeId) {
        this();
        this.title = title;
        this.author = author;
        this.description = description;
        this.setYoutubeId(youtubeId);
    }

    @Column(name = "title", nullable = false)
    @Size(min = 2, max = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    @Size(min = 2, max = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youtubeId", nullable = false, updatable = false)
    @Pattern(regexp = "[a-zA-Z0-9_\\-]{11}")
    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId.substring(youtubeId.lastIndexOf("=") + 1);
    }

    @Column(name = "views")
    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @Column(name = "status", nullable = false)
    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }
}
