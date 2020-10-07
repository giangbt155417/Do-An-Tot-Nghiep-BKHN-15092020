package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PictureArea.
 */
@Entity
@Table(name = "tbl_picture_area")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PictureArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "pos_x", nullable = false)
    private Integer posX;

    @NotNull
    @Column(name = "pos_y", nullable = false)
    private Integer posY;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Column(name = "index", nullable = false)
    private Integer index;

    @NotNull
    @Column(name = "is_fix", nullable = false)
    private Boolean isFix;

    @NotNull
    @Column(name = "object_fit", nullable = false)
    private Integer objectFit;

    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "link_data")
    private Integer linkData;

    @NotNull
    @Column(name = "jhi_on", nullable = false)
    private Integer on;

    @NotNull
    @Column(name = "off", nullable = false)
    private Integer off;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "content_id", nullable = false)
    private Long contentId;

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

    public PictureArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosX() {
        return posX;
    }

    public PictureArea posX(Integer posX) {
        this.posX = posX;
        return this;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public PictureArea posY(Integer posY) {
        this.posY = posY;
        return this;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public PictureArea width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public PictureArea height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getIndex() {
        return index;
    }

    public PictureArea index(Integer index) {
        this.index = index;
        return this;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean isIsFix() {
        return isFix;
    }

    public PictureArea isFix(Boolean isFix) {
        this.isFix = isFix;
        return this;
    }

    public void setIsFix(Boolean isFix) {
        this.isFix = isFix;
    }

    public Integer getObjectFit() {
        return objectFit;
    }

    public PictureArea objectFit(Integer objectFit) {
        this.objectFit = objectFit;
        return this;
    }

    public void setObjectFit(Integer objectFit) {
        this.objectFit = objectFit;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public PictureArea mediaId(Long mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getLinkData() {
        return linkData;
    }

    public PictureArea linkData(Integer linkData) {
        this.linkData = linkData;
        return this;
    }

    public void setLinkData(Integer linkData) {
        this.linkData = linkData;
    }

    public Integer getOn() {
        return on;
    }

    public PictureArea on(Integer on) {
        this.on = on;
        return this;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public Integer getOff() {
        return off;
    }

    public PictureArea off(Integer off) {
        this.off = off;
        return this;
    }

    public void setOff(Integer off) {
        this.off = off;
    }

    public String getType() {
        return type;
    }

    public PictureArea type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getContentId() {
        return contentId;
    }

    public PictureArea contentId(Long contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PictureArea)) {
            return false;
        }
        return id != null && id.equals(((PictureArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PictureArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", posX=" + getPosX() +
            ", posY=" + getPosY() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", index=" + getIndex() +
            ", isFix='" + isIsFix() + "'" +
            ", objectFit=" + getObjectFit() +
            ", mediaId=" + getMediaId() +
            ", linkData=" + getLinkData() +
            ", on=" + getOn() +
            ", off=" + getOff() +
            ", type='" + getType() + "'" +
            ", contentId=" + getContentId() +
            "}";
    }
}
