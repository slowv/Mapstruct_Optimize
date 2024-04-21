package com.example.unscape.entity.video;

import com.example.unscape.entity.AbstractAuditingEntity;
import com.example.unscape.entity.common.CategoryEntity;
import com.example.unscape.entity.common.ReviewEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Video entity
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VideoEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(name = "id")
    private String id;

    /**
     * Tiêu đề
     */
    @NotNull
    @Size(max = 250)
    @Column(name = "title", length = 250, nullable = false)
    private String title;

    /**
     * Nội dung
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * Code
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "code", length = 10, nullable = false, unique = true)
    private String code;

    /**
     * Ảnh preview
     */
    @NotNull
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    /**
     * Thời lượng video
     */
    @NotNull
    @Column(name = "duration", nullable = false)
    private String duration;

    @JsonIgnoreProperties(value = { "video" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    @ToString.Exclude
    private ReviewEntity review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "videos" }, allowSetters = true)
    @ToString.Exclude
    private LabelEntity videoLabel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Transient
    @JsonIgnoreProperties(value = { "video", "resolution" }, allowSetters = true)
    @ToString.Exclude
    private Set<VideoURLEntity> videoURLS = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_video__category",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "videos" }, allowSetters = true)
    @ToString.Exclude
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_video__resolution",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "resolution_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "videoURLS", "videos" }, allowSetters = true)
    @ToString.Exclude
    private Set<ResolutionEntity> resolutions = new HashSet<>();

    public VideoEntity removeResolution(ResolutionEntity resolution) {
        this.resolutions.remove(resolution);
        return this;
    }

    public VideoEntity addResolution(ResolutionEntity resolution) {
        this.resolutions.add(resolution);
        return this;
    }

    public VideoEntity addCategory(CategoryEntity category) {
        this.categories.add(category);
        return this;
    }

    public VideoEntity removeCategory(CategoryEntity category) {
        this.categories.remove(category);
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final VideoEntity that = (VideoEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
