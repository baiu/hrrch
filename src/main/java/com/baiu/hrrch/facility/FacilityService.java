package com.baiu.hrrch.facility;

import java.util.List;

public interface FacilityService {

    Facility create(Facility facility);

    Facility get(Facility facility);

    Facility update(Facility facility);

    void delete(Facility facility);

    List<Facility> getAll();
}
