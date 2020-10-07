package program.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link program.domain.TextArea} entity.
 */
public class TextAreaDTO implements Serializable {
    
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
    private String text;

    @NotNull
    private String fontName;

    @NotNull
    private String fontSize;

    private Boolean isBold;

    private Boolean isItalic;

    @NotNull
    private Integer horizontalAlignment;

    @NotNull
    private Integer verticalAlignment;

    @NotNull
    private String fontColor;

    @NotNull
    private String backgroundColor;

    @NotNull
    private Integer scrollStatus;

    @NotNull
    private Integer scrollDirection;

    @NotNull
    private Integer scrollSpeed;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean isIsBold() {
        return isBold;
    }

    public void setIsBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public Boolean isIsItalic() {
        return isItalic;
    }

    public void setIsItalic(Boolean isItalic) {
        this.isItalic = isItalic;
    }

    public Integer getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(Integer horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public Integer getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(Integer verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getScrollStatus() {
        return scrollStatus;
    }

    public void setScrollStatus(Integer scrollStatus) {
        this.scrollStatus = scrollStatus;
    }

    public Integer getScrollDirection() {
        return scrollDirection;
    }

    public void setScrollDirection(Integer scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    public Integer getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
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
        if (!(o instanceof TextAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((TextAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TextAreaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", posX=" + getPosX() +
            ", posY=" + getPosY() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", index=" + getIndex() +
            ", isFix='" + isIsFix() + "'" +
            ", text='" + getText() + "'" +
            ", fontName='" + getFontName() + "'" +
            ", fontSize='" + getFontSize() + "'" +
            ", isBold='" + isIsBold() + "'" +
            ", isItalic='" + isIsItalic() + "'" +
            ", horizontalAlignment=" + getHorizontalAlignment() +
            ", verticalAlignment=" + getVerticalAlignment() +
            ", fontColor='" + getFontColor() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", scrollStatus=" + getScrollStatus() +
            ", scrollDirection=" + getScrollDirection() +
            ", scrollSpeed=" + getScrollSpeed() +
            ", linkData=" + getLinkData() +
            ", on=" + getOn() +
            ", off=" + getOff() +
            ", type='" + getType() + "'" +
            ", contentId=" + getContentId() +
            "}";
    }
}
