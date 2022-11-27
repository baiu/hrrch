package com.baiu.hrrch.facility;

import lombok.EqualsAndHashCode;
import com.baiu.hrrch.group.Group;
import com.baiu.hrrch.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "facility_simple")
@EqualsAndHashCode
public class FacilitySimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guid;
    private LocalDateTime createDate;
    @ManyToOne
    private Person createPerson;
    private boolean actual;
    private Integer idx;
    private String parentGuid;
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
    @ManyToMany()
    @JoinTable(name = "group_facility",
            joinColumns = @JoinColumn(name = "facility_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Person getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Person createPerson) {
        this.createPerson = createPerson;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

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
}
