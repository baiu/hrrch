package com.baiu.hrrch.facility;

import java.util.List;

public interface FacilitySimpleService {
    FacilitySimple create(FacilitySimple facility);

    FacilitySimple get(FacilitySimple facility);

    FacilitySimple update(FacilitySimple facility);

    void delete(FacilitySimple facility);

    List<FacilitySimple> getAll();
}
