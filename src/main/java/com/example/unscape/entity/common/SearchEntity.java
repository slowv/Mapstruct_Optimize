package com.example.unscape.entity.common;

import com.example.unscape.entity.AbstractAuditingEntity;
import com.example.unscape.entity.enums.SearchType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Search.
 */
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "search",
        indexes = {
                @Index(columnList = "keywords"),
                @Index(columnList = "type")
        }
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SearchEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(name = "id")
    private String id;

    /**
     * Từ khóa
     */
    @NonNull
    @Column(name = "keywords", updatable = false, nullable = false)
    private String keywords;

    /**
     * Tổng số lần search
     */
    @Column(name = "v_count")
    private Long count = 0L;

    /**
     * Kiểu search
     */
    @NonNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SearchType type;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final SearchEntity that = (SearchEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
