package com.kpvala.usercrud.repository;

import com.kpvala.usercrud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
