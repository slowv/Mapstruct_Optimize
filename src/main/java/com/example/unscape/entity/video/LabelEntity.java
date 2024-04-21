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

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A VideoLabel.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "video_label")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LabelEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    /**
     * Tên nhãn
     */
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "videoLabel")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Transient
    @JsonIgnoreProperties(value = { "review", "videoURLS", "categories", "resolutions", "label", "actors" }, allowSetters = true)
    @ToString.Exclude
    private Set<VideoEntity> videos = new HashSet<>();

    public void setVideos(Set<VideoEntity> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.setVideoLabel(null));
        }
        if (videos != null) {
            videos.forEach(i -> i.setVideoLabel(this));
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
        final LabelEntity that = (LabelEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
