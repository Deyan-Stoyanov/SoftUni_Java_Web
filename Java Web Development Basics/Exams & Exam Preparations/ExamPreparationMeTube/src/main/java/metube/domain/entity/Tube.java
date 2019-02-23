package metube.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {
    private String name;
    private String author;
    private String description;
    private String youtubeId;
    private User uploader;

    public Tube() {
    }

    public Tube(String name, String author, String description, String youtubeId, User uploader) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.youtubeId = youtubeId;
        this.uploader = uploader;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youtube_id", unique = true, nullable = false)
    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
