package com.projects.splitwise.repositories;

import com.projects.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserNameOrPhoneNumber(String userName, String phoneNumber);

}
