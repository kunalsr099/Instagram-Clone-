package com.lpu.Momento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id IN :users")
    List<User> findByIds(@Param("users") List<Long> userIds);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%")
    List<User> findByQuery(@Param("query") String query);

	
}
