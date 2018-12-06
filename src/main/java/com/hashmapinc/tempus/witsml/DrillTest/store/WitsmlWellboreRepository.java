package com.hashmapinc.tempus.witsml.DrillTest.store;

import org.springframework.data.repository.CrudRepository;

public interface WitsmlWellboreRepository extends CrudRepository<WitsmlWellbore, Long> {

    WitsmlWellbore findByUid(String uid);
}
