package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RouteContent.
 */
@Entity
@Table(name = "tbl_route_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RouteContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator")
    private Long id;

    @NotNull
    @Column(name = "route_id", nullable = false)
    private Long routeId;

    @NotNull
    @Column(name = "content", nullable = false)
    private Long content;

    @NotNull
    @Column(name = "display", nullable = false)
    private Integer display;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public RouteContent routeId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getContent() {
        return content;
    }

    public RouteContent content(Long content) {
        this.content = content;
        return this;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public Integer getDisplay() {
        return display;
    }

    public RouteContent display(Integer display) {
        this.display = display;
        return this;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteContent)) {
            return false;
        }
        return id != null && id.equals(((RouteContent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RouteContent{" +
            "id=" + getId() +
            ", routeId=" + getRouteId() +
            ", content=" + getContent() +
            ", display=" + getDisplay() +
            "}";
    }
}
