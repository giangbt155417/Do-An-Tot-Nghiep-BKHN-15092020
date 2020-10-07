package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BusStop.
 */
@Entity
@Table(name = "tbl_bus_stop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusStop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "bus_stop_no", nullable = false)
    private String busStopNo;

    @NotNull
    @Column(name = "suffix", nullable = false)
    private String suffix;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private String createdDate;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BusStop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusStopNo() {
        return busStopNo;
    }

    public BusStop busStopNo(String busStopNo) {
        this.busStopNo = busStopNo;
        return this;
    }

    public void setBusStopNo(String busStopNo) {
        this.busStopNo = busStopNo;
    }

    public String getSuffix() {
        return suffix;
    }

    public BusStop suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public BusStop createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public BusStop description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusStop)) {
            return false;
        }
        return id != null && id.equals(((BusStop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusStop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", busStopNo='" + getBusStopNo() + "'" +
            ", suffix='" + getSuffix() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
