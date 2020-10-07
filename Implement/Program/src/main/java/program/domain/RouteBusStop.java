package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RouteBusStop.
 */
@Entity
@Table(name = "tbl_route_bus_stop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RouteBusStop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator")
    private Long id;

    @NotNull
    @Column(name = "route_id", nullable = false)
    private Long routeId;

    @NotNull
    @Column(name = "bus_stop_id", nullable = false)
    private Long busStopId;

    @NotNull
    @Column(name = "order_no", nullable = false)
    private Long orderNo;

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

    public RouteBusStop routeId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getBusStopId() {
        return busStopId;
    }

    public RouteBusStop busStopId(Long busStopId) {
        this.busStopId = busStopId;
        return this;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public RouteBusStop orderNo(Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteBusStop)) {
            return false;
        }
        return id != null && id.equals(((RouteBusStop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RouteBusStop{" +
            "id=" + getId() +
            ", routeId=" + getRouteId() +
            ", busStopId=" + getBusStopId() +
            ", orderNo=" + getOrderNo() +
            "}";
    }
}
