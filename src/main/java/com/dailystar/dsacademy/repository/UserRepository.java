package com.dailystar.dsacademy.repository;

import com.dailystar.dsacademy.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface UserRepository extends MongoRepository<User, String> {

}
