package com.telemedine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telemedine.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String username);

	Boolean existsByPhone(String username);

	Boolean existsByEmail(String email);
}
