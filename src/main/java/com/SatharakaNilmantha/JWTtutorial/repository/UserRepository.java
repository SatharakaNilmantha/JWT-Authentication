package com.SatharakaNilmantha.JWTtutorial.repository;

import com.SatharakaNilmantha.JWTtutorial.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity,String> {

    Optional<UserEntity> findByUsername(String username );
}
