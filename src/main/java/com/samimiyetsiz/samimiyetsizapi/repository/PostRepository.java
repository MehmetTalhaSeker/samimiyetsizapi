package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByCategory(Pageable pageable, Category category);

    @Query("SELECT p FROM Post p WHERE p.createdDate > ?1 ")
    List<Post> findInLastHours(Instant hour);

}
