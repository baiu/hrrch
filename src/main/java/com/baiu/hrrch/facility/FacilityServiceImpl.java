package com.baiu.hrrch.facility;

import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility create(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public Facility get(Facility facility) {
        return facilityRepository.findById(facility.getId())
                .orElseThrow(() -> new EntityNotFoundException(facility.getClass(), facility.getId()));
    }

    @Override
    public Facility update(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public void delete(Facility facility) {
        facilityRepository.delete(facility);
    }

    @Override
    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }
}
