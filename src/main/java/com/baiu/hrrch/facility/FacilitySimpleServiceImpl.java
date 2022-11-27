package com.baiu.hrrch.facility;

import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitySimpleServiceImpl implements FacilitySimpleService {

    private final FacilitySimpleRepository facilitySimpleRepository;

    public FacilitySimpleServiceImpl(FacilitySimpleRepository facilitySimpleRepository) {
        this.facilitySimpleRepository = facilitySimpleRepository;
    }

    @Override
    public FacilitySimple create(FacilitySimple facility) {
        return facilitySimpleRepository.save(facility);
    }

    @Override
    public FacilitySimple get(FacilitySimple facility) {
        return facilitySimpleRepository.findById(facility.getId())
                .orElseThrow(() -> new EntityNotFoundException(facility.getClass(), facility.getId()));
    }

    @Override
    public FacilitySimple update(FacilitySimple facility) {
        return facilitySimpleRepository.save(facility);
    }

    @Override
    public void delete(FacilitySimple facility) {
        facilitySimpleRepository.delete(facility);
    }

    @Override
    public List<FacilitySimple> getAll() {
        return facilitySimpleRepository.findAll();
    }
}
