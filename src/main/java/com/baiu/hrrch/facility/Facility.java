package com.baiu.hrrch.facility;

import com.baiu.hrrch.attribute.AttributeValue;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "facility")
public class Facility {
    @Id
    private ObjectId id;
    @Indexed
    private String guid;
    private LocalDateTime createDate;
    private Long createPersonId;
    private boolean actual;
    private Integer idx;
    private String parentGuid;
    @TextIndexed
    private String name;
    private String building_structures_guid;
    private String station_number;
    private String factory_number;
    private String facility_type_guid;
    private String factory_manufacturer_guid;
    private Integer manufacture_year;
    private Integer commissioning_year;
    private Integer passport_availability;
    private String product_type_guid;
    private String office_branch_guid;
    @Indexed
    private UUID globalId;
    private List<AttributeValue> attrs;
    @TextIndexed
    private String desc;
    @TextScore
    private Float score;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

    public String getBuilding_structures_guid() {
        return building_structures_guid;
    }

    public void setBuilding_structures_guid(String building_structures_guid) {
        this.building_structures_guid = building_structures_guid;
    }

    public String getStation_number() {
        return station_number;
    }

    public void setStation_number(String station_number) {
        this.station_number = station_number;
    }

    public String getFactory_number() {
        return factory_number;
    }

    public void setFactory_number(String factory_number) {
        this.factory_number = factory_number;
    }

    public String getFacility_type_guid() {
        return facility_type_guid;
    }

    public void setFacility_type_guid(String facility_type_guid) {
        this.facility_type_guid = facility_type_guid;
    }

    public String getFactory_manufacturer_guid() {
        return factory_manufacturer_guid;
    }

    public void setFactory_manufacturer_guid(String factory_manufacturer_guid) {
        this.factory_manufacturer_guid = factory_manufacturer_guid;
    }

    public Integer getManufacture_year() {
        return manufacture_year;
    }

    public void setManufacture_year(Integer manufacture_year) {
        this.manufacture_year = manufacture_year;
    }

    public Integer getCommissioning_year() {
        return commissioning_year;
    }

    public void setCommissioning_year(Integer commissioning_year) {
        this.commissioning_year = commissioning_year;
    }

    public Integer getPassport_availability() {
        return passport_availability;
    }

    public void setPassport_availability(Integer passport_availability) {
        this.passport_availability = passport_availability;
    }

    public String getProduct_type_guid() {
        return product_type_guid;
    }

    public void setProduct_type_guid(String product_type_guid) {
        this.product_type_guid = product_type_guid;
    }

    public String getOffice_branch_guid() {
        return office_branch_guid;
    }

    public void setOffice_branch_guid(String office_branch_guid) {
        this.office_branch_guid = office_branch_guid;
    }

    public Facility() {

    }

    public Facility(String id) {
        this.id = new ObjectId(id);
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
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

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public UUID getGlobalId() {
        return globalId;
    }

    public void setGlobalId(UUID globalId) {
        this.globalId = globalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttributeValue> attrs) {
        this.attrs = attrs;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
