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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Chapter.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "chapter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChapterEntity extends AbstractAuditingEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    /**
     * Tiêu đề
     */
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chapter")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "chapter" }, allowSetters = true)
    @ToString.Exclude
    private Set<PageStoryEntity> pageStories = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "chapters" }, allowSetters = true)
    private StoryBookEntity story;

    public void setPages(Set<PageStoryEntity> pageStories) {
        if (this.pageStories != null) {
            this.pageStories.forEach(i -> i.setChapter(null));
        }
        if (pageStories != null) {
            pageStories.forEach(i -> i.setChapter(this));
        }
        this.pageStories = pageStories;
    }

    public ChapterEntity addPage(PageStoryEntity pageStory) {
        this.pageStories.add(pageStory);
        pageStory.setChapter(this);
        return this;
    }

    public ChapterEntity removePage(PageStoryEntity pageStory) {
        this.pageStories.remove(pageStory);
        pageStory.setChapter(null);
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final ChapterEntity that = (ChapterEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
