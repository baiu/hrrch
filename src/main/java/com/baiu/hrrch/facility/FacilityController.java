package com.baiu.hrrch.facility;

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

import java.util.List;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/facility")
public class FacilityController {
    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Facility create(@RequestBody Facility facility) {
        return facilityService.create(facility);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Facility getById(@PathVariable String id) {
        return facilityService.get(new Facility(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Facility update(@RequestBody Facility facility) {
        return facilityService.update(facility);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        facilityService.delete(new Facility(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Facility> getAll() {
        return facilityService.getAll();
    }
}
