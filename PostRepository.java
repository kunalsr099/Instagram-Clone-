package com.lpu.Momento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdAt DESC")
    List<Post> findAllPostByUserIds(@Param("users") List<Long> userIds);
}
