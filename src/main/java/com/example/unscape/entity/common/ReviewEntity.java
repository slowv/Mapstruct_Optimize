package com.example.unscape.entity.common;

import com.example.unscape.entity.AbstractAuditingEntity;
import com.example.unscape.entity.video.VideoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Review entity
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "review")
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReviewEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    /**
     * Lượt thích
     */
    @Column(name = "v_like")
    private Long like;

    /**
     * Lượt không thích
     */
    @Column(name = "dislike")
    private Long dislike;

    /**
     * Lượt xem
     */
    @Column(name = "view")
    private Long view;

    /**
     * Lượt yêu thích
     */
    @Column(name = "favorite")
    private Long favorite;

    @JsonIgnoreProperties(value = { "review", "videoURLS", "categories", "resolutions", "label", "actors" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "review")
    @Transient
    private VideoEntity video;

    public void setVideo(VideoEntity video) {
        if (this.video != null) {
            this.video.setReview(null);
        }
        if (video != null) {
            video.setReview(this);
        }
        this.video = video;
    }
}
