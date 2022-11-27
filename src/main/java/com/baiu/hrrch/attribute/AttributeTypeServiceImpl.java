package com.baiu.hrrch.attribute;

import com.baiu.hrrch.doc.Doc;
import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

@Service
public class AttributeTypeServiceImpl implements AttributeTypeService {
    private final AttributeTypeRepository attributeTypeRepository;
    private final AttributeOptionRepository attributeOptionRepository;

    @Autowired
    public AttributeTypeServiceImpl(AttributeTypeRepository attributeTypeRepository, AttributeOptionRepository attributeOptionRepository) {
        this.attributeTypeRepository = attributeTypeRepository;
        this.attributeOptionRepository = attributeOptionRepository;
    }

    @Override
    public AttributeType createAttributeType(AttributeType attributeType) {
        return attributeTypeRepository.insert(attributeType);
    }

    @Override
    public AttributeType getAttributeType(AttributeType attributeType) {
        return attributeTypeRepository.findById(attributeType.getGuid())
                .orElseThrow(() -> new EntityNotFoundException(attributeType.getClass(), attributeType.getId()));
    }

    @Override
    public AttributeType updateAttributeType(AttributeType attributeType) {
        return attributeTypeRepository.update(attributeType);
    }

    @Override
    public void deleteAttributeType(AttributeType attributeType) {
        attributeTypeRepository.delete(attributeType);
    }

    @Override
    public Collection<AttributeType> getAllDocAttribute(Person person) {
        return attributeTypeRepository.findAll();
    }

    @Override
    public Collection<AttributeType> getAllByDoc(Doc doc) {
        return null;
    }

    @Override
    public Collection<AttributeType> getByCategory(Catalog catalog) {
        return null;
    }

    @Override
    public boolean attributeExist(Doc doc) {
        return false;
    }

    @Override
    public AttributeOption createAttributeOption(AttributeType attributeType, String option) {
        return null;
    }

    @Override
    public List<AttributeType> getAllAttributeTypes() {
        return attributeTypeRepository.findAll();
    }
}
