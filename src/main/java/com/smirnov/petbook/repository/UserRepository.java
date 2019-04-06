package com.smirnov.petbook.repository;

import com.smirnov.petbook.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}