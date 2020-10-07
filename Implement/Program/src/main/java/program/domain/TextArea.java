package program.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TextArea.
 */
@Entity
@Table(name = "tbl_text_area")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TextArea implements Serializable {

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
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "font_name", nullable = false)
    private String fontName;

    @NotNull
    @Column(name = "font_size", nullable = false)
    private String fontSize;

    @Column(name = "is_bold")
    private Boolean isBold;

    @Column(name = "is_italic")
    private Boolean isItalic;

    @NotNull
    @Column(name = "horizontal_alignment", nullable = false)
    private Integer horizontalAlignment;

    @NotNull
    @Column(name = "vertical_alignment", nullable = false)
    private Integer verticalAlignment;

    @NotNull
    @Column(name = "font_color", nullable = false)
    private String fontColor;

    @NotNull
    @Column(name = "background_color", nullable = false)
    private String backgroundColor;

    @NotNull
    @Column(name = "scroll_status", nullable = false)
    private Integer scrollStatus;

    @NotNull
    @Column(name = "scroll_direction", nullable = false)
    private Integer scrollDirection;

    @NotNull
    @Column(name = "scroll_speed", nullable = false)
    private Integer scrollSpeed;

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

    public TextArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosX() {
        return posX;
    }

    public TextArea posX(Integer posX) {
        this.posX = posX;
        return this;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public TextArea posY(Integer posY) {
        this.posY = posY;
        return this;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public TextArea width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public TextArea height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getIndex() {
        return index;
    }

    public TextArea index(Integer index) {
        this.index = index;
        return this;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean isIsFix() {
        return isFix;
    }

    public TextArea isFix(Boolean isFix) {
        this.isFix = isFix;
        return this;
    }

    public void setIsFix(Boolean isFix) {
        this.isFix = isFix;
    }

    public String getText() {
        return text;
    }

    public TextArea text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontName() {
        return fontName;
    }

    public TextArea fontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontSize() {
        return fontSize;
    }

    public TextArea fontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean isIsBold() {
        return isBold;
    }

    public TextArea isBold(Boolean isBold) {
        this.isBold = isBold;
        return this;
    }

    public void setIsBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public Boolean isIsItalic() {
        return isItalic;
    }

    public TextArea isItalic(Boolean isItalic) {
        this.isItalic = isItalic;
        return this;
    }

    public void setIsItalic(Boolean isItalic) {
        this.isItalic = isItalic;
    }

    public Integer getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public TextArea horizontalAlignment(Integer horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public void setHorizontalAlignment(Integer horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public Integer getVerticalAlignment() {
        return verticalAlignment;
    }

    public TextArea verticalAlignment(Integer verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public void setVerticalAlignment(Integer verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getFontColor() {
        return fontColor;
    }

    public TextArea fontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public TextArea backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getScrollStatus() {
        return scrollStatus;
    }

    public TextArea scrollStatus(Integer scrollStatus) {
        this.scrollStatus = scrollStatus;
        return this;
    }

    public void setScrollStatus(Integer scrollStatus) {
        this.scrollStatus = scrollStatus;
    }

    public Integer getScrollDirection() {
        return scrollDirection;
    }

    public TextArea scrollDirection(Integer scrollDirection) {
        this.scrollDirection = scrollDirection;
        return this;
    }

    public void setScrollDirection(Integer scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    public Integer getScrollSpeed() {
        return scrollSpeed;
    }

    public TextArea scrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
        return this;
    }

    public void setScrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public Integer getLinkData() {
        return linkData;
    }

    public TextArea linkData(Integer linkData) {
        this.linkData = linkData;
        return this;
    }

    public void setLinkData(Integer linkData) {
        this.linkData = linkData;
    }

    public Integer getOn() {
        return on;
    }

    public TextArea on(Integer on) {
        this.on = on;
        return this;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public Integer getOff() {
        return off;
    }

    public TextArea off(Integer off) {
        this.off = off;
        return this;
    }

    public void setOff(Integer off) {
        this.off = off;
    }

    public String getType() {
        return type;
    }

    public TextArea type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getContentId() {
        return contentId;
    }

    public TextArea contentId(Long contentId) {
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
        if (!(o instanceof TextArea)) {
            return false;
        }
        return id != null && id.equals(((TextArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TextArea{" +
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
