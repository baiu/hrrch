package com.baiu.hrrch.attribute;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AttributeOptionServiceImpl implements AttributeOptionService {

    private final AttributeOptionRepository attributeOptionRepository;

    public AttributeOptionServiceImpl(AttributeOptionRepository attributeOptionRepository) {
        this.attributeOptionRepository = attributeOptionRepository;
    }

    @Override
    public AttributeOption createAttributeOption(AttributeOption attributeOption) {
        return attributeOptionRepository.save(attributeOption);
    }

    @Override
    public AttributeOption getAttributeOption(AttributeOption attributeOption) {
        return attributeOptionRepository.getOne(attributeOption.getId());
    }

    @Override
    public AttributeOption updateAttributeOption(AttributeOption attributeOption) {
        return attributeOptionRepository.save(attributeOption);
    }

    @Override
    public void deleteAttributeOption(Long attributeOptionId) {
        attributeOptionRepository.deleteById(attributeOptionId);
    }

    @Override
    public Collection<AttributeOption> getAttributeOptions(AttributeType attributeType) {
        return attributeOptionRepository.getAllOptionForAttributeType(attributeType);
    }
}
