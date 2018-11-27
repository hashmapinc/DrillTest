package com.hashmapinc.tempus.witsml.DrillTest.store;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByuserName(String userName);
}
