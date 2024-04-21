package com.example.unscape.entity.video;

import com.example.unscape.entity.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Resolution.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "resolution")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResolutionEntity extends AbstractAuditingEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resolution")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "video", "resolution" }, allowSetters = true)
    @ToString.Exclude
    private Set<VideoURLEntity> videoURLS = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "resolutions")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Transient
    @JsonIgnoreProperties(value = { "review", "videoURLS", "categories", "resolutions", "label", "actors" }, allowSetters = true)
    @ToString.Exclude
    private Set<VideoEntity> videos = new HashSet<>();

    public void setVideoURLS(Set<VideoURLEntity> videoURLS) {
        if (this.videoURLS != null) {
            this.videoURLS.forEach(i -> i.setResolution(null));
        }
        if (videoURLS != null) {
            videoURLS.forEach(i -> i.setResolution(this));
        }
        this.videoURLS = videoURLS;
    }

    public void setVideos(Set<VideoEntity> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.removeResolution(this));
        }
        if (videos != null) {
            videos.forEach(i -> i.addResolution(this));
        }
        this.videos = videos;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final ResolutionEntity that = (ResolutionEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
