package program.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link program.domain.PictureArea} entity.
 */
public class PictureAreaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer posX;

    @NotNull
    private Integer posY;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;

    @NotNull
    private Integer index;

    @NotNull
    private Boolean isFix;

    @NotNull
    private Integer objectFit;

    private Long mediaId;

    private Integer linkData;

    @NotNull
    private Integer on;

    @NotNull
    private Integer off;

    @NotNull
    private String type;

    @NotNull
    private Long contentId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean isIsFix() {
        return isFix;
    }

    public void setIsFix(Boolean isFix) {
        this.isFix = isFix;
    }

    public Integer getObjectFit() {
        return objectFit;
    }

    public void setObjectFit(Integer objectFit) {
        this.objectFit = objectFit;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getLinkData() {
        return linkData;
    }

    public void setLinkData(Integer linkData) {
        this.linkData = linkData;
    }

    public Integer getOn() {
        return on;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public Integer getOff() {
        return off;
    }

    public void setOff(Integer off) {
        this.off = off;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PictureAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((PictureAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PictureAreaDTO{" +
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
