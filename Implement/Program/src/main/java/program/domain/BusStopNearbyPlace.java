package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BusStopNearbyPlace.
 */
@Entity
@Table(name = "tbl_bus_stop_nearby_place")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusStopNearbyPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator")
    private Long id;

    @NotNull
    @Column(name = "bus_stop_id", nullable = false)
    private Long busStopId;

    @NotNull
    @Column(name = "nearby_place_id", nullable = false)
    private Long nearbyPlaceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusStopId() {
        return busStopId;
    }

    public BusStopNearbyPlace busStopId(Long busStopId) {
        this.busStopId = busStopId;
        return this;
    }

    public void setBusStopId(Long busStopId) {
        this.busStopId = busStopId;
    }

    public Long getNearbyPlaceId() {
        return nearbyPlaceId;
    }

    public BusStopNearbyPlace nearbyPlaceId(Long nearbyPlaceId) {
        this.nearbyPlaceId = nearbyPlaceId;
        return this;
    }

    public void setNearbyPlaceId(Long nearbyPlaceId) {
        this.nearbyPlaceId = nearbyPlaceId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusStopNearbyPlace)) {
            return false;
        }
        return id != null && id.equals(((BusStopNearbyPlace) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusStopNearbyPlace{" +
            "id=" + getId() +
            ", busStopId=" + getBusStopId() +
            ", nearbyPlaceId=" + getNearbyPlaceId() +
            "}";
    }
}
