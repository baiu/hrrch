package com.baiu.hrrch.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@Secured("ROLE_MANAGER")
@RequestMapping("/api/attributes")
public class AttributeTypesController {
    private final AttributeTypeService attributeTypeService;
    private final AttributeOptionService attributeOptionService;

    @Autowired
    public AttributeTypesController(AttributeTypeService attributeTypeService, AttributeOptionService attributeOptionService) {
        this.attributeTypeService = attributeTypeService;
        this.attributeOptionService = attributeOptionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AttributeType create(@RequestBody AttributeType attribute) {
        return attributeTypeService.createAttributeType(attribute);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{attributeId}")
    @Secured("ROLE_USER")
    public AttributeType getById(@PathVariable Long attributeId) {
        return attributeTypeService.getAttributeType(new AttributeType(attributeId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public AttributeType update(@RequestBody AttributeType attribute) {
        return attributeTypeService.updateAttributeType(attribute);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{attributeId}")
    public void delete(@PathVariable Long attributeId) {
        attributeTypeService.deleteAttributeType(new AttributeType(attributeId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AttributeType> getAll() {
        return attributeTypeService.getAllAttributeTypes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/option")
    public AttributeOption create(@RequestBody AttributeOption option) {
        return attributeOptionService.createAttributeOption(option);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/option")
    public AttributeOption update(@RequestBody AttributeOption option) {
        return attributeOptionService.updateAttributeOption(option);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/option/{optionId}")
    public void deleteOption(@PathVariable Long optionId) {
        attributeOptionService.deleteAttributeOption(optionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{attributeId}/options")
    public Collection<AttributeOption> getAllOptionsByAttributeType(@PathVariable Long attributeId) {
        return attributeOptionService.getAttributeOptions(new AttributeType(attributeId));
    }
}
