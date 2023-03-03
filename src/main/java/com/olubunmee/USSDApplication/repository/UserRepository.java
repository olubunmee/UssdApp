package com.olubunmee.USSDApplication.repository;

import com.olubunmee.USSDApplication.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumber(String phone);
    boolean existsByPhoneNumber(String phone);
}
