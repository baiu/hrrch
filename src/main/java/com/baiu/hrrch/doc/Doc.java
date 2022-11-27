package com.baiu.hrrch.doc;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;
import com.baiu.hrrch.attribute.AttributeValue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Основная сущность в системе. Карточка документа.
 * Хранит в себе наименование, атрибутивный состав
 *
 * @see DocService
 */
@Document(collection = "docs")
public class Doc {
    @Id
    private ObjectId id;
    private LocalDateTime createDate;
    private Long createPersonId;
    private boolean active;
    private int version;

    /**
     * Глобальный идентификатор документа
     */
    @Indexed
    private UUID globalId;

    /**
     * Наименование документа
     */
    @TextIndexed
    private String name;

    /**
     * Список каталогов в которых состоит документ
     */
    private Set<Long> parentCatalogsIds = new HashSet<>();

    /**
     * Множество каталогов включая предков в которых состоит документ
     */
    private Set<Long> ancestorCatalogsIds = new HashSet<>();

    /**
     * Атрибутный состав
     */
    private List<AttributeValue> catalogAttrs;

    /**
     * Атрибутный состав характерный для документа
     */
    private List<AttributeValue> docAttrs;

    /**
     * Описание документа
     */
    @TextIndexed
    private String desc;

    /**
     * Адрес физического документа
     */
    private DocAddress address;

    /**
     * Количество страниц физического документа
     */
    private int listCount;

    /**
     * Дата истечения срока хранения
     * документов.
     */
    private LocalDateTime destructionDate;

    /**
     * Дата истечения срока обновления
     * документов.
     */
    private LocalDateTime cyclicDate;

    @TextScore
    private Float score;

    public Doc() {
    }

    public Doc(String id) {
        setId(id);
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributeValue> getCatalogAttrs() {
        return catalogAttrs;
    }

    public void setCatalogAttrs(List<AttributeValue> catalogAttrs) {
        this.catalogAttrs = catalogAttrs;
    }

    public List<AttributeValue> getDocAttrs() {
        return docAttrs;
    }

    public void setDocAttrs(List<AttributeValue> docAttrs) {
        this.docAttrs = docAttrs;
    }

    public Set<Long> getParentCatalogsIds() {
        return parentCatalogsIds;
    }

    public void setParentCatalogsIds(Set<Long> parentCatalogsIds) {
        this.parentCatalogsIds = parentCatalogsIds;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getDestructionDate() {
        return destructionDate;
    }

    public void setDestructionDate(LocalDateTime destructionDate) {
        this.destructionDate = destructionDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Long createPersonId) {
        this.createPersonId = createPersonId;
    }

    public DocAddress getAddress() {
        return address;
    }

    public void setAddress(DocAddress address) {
        this.address = address;
    }

    public LocalDateTime getCyclicDate() {
        return cyclicDate;
    }

    public void setCyclicDate(LocalDateTime cyclicDate) {
        this.cyclicDate = cyclicDate;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public Set<Long> getAncestorCatalogsIds() {
        return ancestorCatalogsIds;
    }

    public void setAncestorCatalogsIds(Set<Long> ancestorCatalogsIds) {
        this.ancestorCatalogsIds = ancestorCatalogsIds;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getGlobalId() {
        return globalId;
    }

    public void setGlobalId(UUID globalId) {
        this.globalId = globalId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
