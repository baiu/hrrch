package com.baiu.hrrch.attribute;

import java.util.Collection;

public interface AttributeOptionService {
    AttributeOption createAttributeOption(AttributeOption attributeOption);

    AttributeOption getAttributeOption(AttributeOption attributeOption);

    AttributeOption updateAttributeOption(AttributeOption attributeOption);

    void deleteAttributeOption(Long attributeOptionId);

    Collection<AttributeOption> getAttributeOptions(AttributeType attributeType);

}
