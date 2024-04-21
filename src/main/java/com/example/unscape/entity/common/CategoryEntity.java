package com.example.unscape.entity.common;

import com.example.unscape.entity.AbstractAuditingEntity;
import com.example.unscape.entity.video.VideoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategoryEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(name = "id")
    private String id;

    /**
     * Tên danh mục
     */
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Transient
    @JsonIgnoreProperties(value = { "review", "videoURLS", "categories", "resolutions", "label", "actors" }, allowSetters = true)
    private Set<VideoEntity> videos = new HashSet<>();

    public void setVideos(Set<VideoEntity> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.removeCategory(this));
        }
        if (videos != null) {
            videos.forEach(i -> i.addCategory(this));
        }
        this.videos = videos;
    }

    public CategoryEntity addVideo(VideoEntity video) {
        this.videos.add(video);
        video.getCategories().add(this);
        return this;
    }

    public CategoryEntity removeVideo(VideoEntity video) {
        this.videos.remove(video);
        video.getCategories().remove(this);
        return this;
    }
}
