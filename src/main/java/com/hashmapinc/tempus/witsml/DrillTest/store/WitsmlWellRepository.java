package com.hashmapinc.tempus.witsml.DrillTest.store;

import org.springframework.data.repository.CrudRepository;

public interface WitsmlWellRepository extends CrudRepository<WitsmlWell, Long> {

    WitsmlWell findByUid(String uid);
}
