package com.baiu.hrrch.attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttributeOptionRepository extends JpaRepository<AttributeOption, Long> {

    @Query("SELECT distinct o FROM attribute_option o join o.attributeType t WHERE t = :attributeType")
    List<AttributeOption> getAllOptionForAttributeType(@Param("attributeType") AttributeType attributeType);
}
