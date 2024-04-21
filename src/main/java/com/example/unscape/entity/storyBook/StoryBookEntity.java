package com.example.unscape.entity.storyBook;

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
 * A StoryBook.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "story_book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoryBookEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    /**
     * Tên truyện
     */
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Nội dung
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * Tác giả
     */
    @Column(name = "author")
    private String author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "story")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "pages", "story" }, allowSetters = true)
    @ToString.Exclude
    private Set<ChapterEntity> chapterEntities = new HashSet<>();

    public void setChapterEntities(Set<ChapterEntity> chapterEntities) {
        if (this.chapterEntities != null) {
            this.chapterEntities.forEach(i -> i.setStory(null));
        }
        if (chapterEntities != null) {
            chapterEntities.forEach(i -> i.setStory(this));
        }
        this.chapterEntities = chapterEntities;
    }

    public StoryBookEntity addChapter(ChapterEntity chapterEntity) {
        this.chapterEntities.add(chapterEntity);
        chapterEntity.setStory(this);
        return this;
    }

    public StoryBookEntity removeChapter(ChapterEntity chapterEntity) {
        this.chapterEntities.remove(chapterEntity);
        chapterEntity.setStory(null);
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final StoryBookEntity that = (StoryBookEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
