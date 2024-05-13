package com.dev.sukran.jwtspringsecurity.repositories;

import com.dev.sukran.jwtspringsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
}
