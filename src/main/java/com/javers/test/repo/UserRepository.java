package com.javers.test.repo;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javers.test.model.User;

@Repository
@JaversSpringDataAuditable
public interface UserRepository extends JpaRepository<User, Integer> {

}
