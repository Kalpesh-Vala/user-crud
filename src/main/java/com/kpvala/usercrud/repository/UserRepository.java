
// UserRepository.java
package com.kpvala.usercrud.repository;

import com.kpvala.usercrud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}