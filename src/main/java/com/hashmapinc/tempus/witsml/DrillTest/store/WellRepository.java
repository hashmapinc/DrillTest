package com.hashmapinc.tempus.witsml.DrillTest.store;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WellRepository extends CrudRepository<Well, Long> {

    Well findByUUid(String uuid);

    Well findByName(String name);
}
