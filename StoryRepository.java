package com.lpu.Momento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("SELECT s FROM Story s WHERE s.user.id = :userId")
    List<Story> findAllStoryByUserId(@Param("userId") Long userId);
}
