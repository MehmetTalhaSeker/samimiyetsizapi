package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Hot;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByCategory(Pageable pageable, Category category);

    LocalDateTime currentTimeMinus4Hours = LocalDateTime.now().minusHours(4L);

    LocalDateTime currentTimeMinus12Hours = LocalDateTime.now().minusHours(12L);


    @Query("SELECT a FROM Post a WHERE a.createdDate > :currentTimeMinus4Hours ORDER BY a.createdDate")
    Page<Post> findAllWithCreationDateTimeInLastFourHours(Pageable pageable);

    @Query("SELECT a FROM Post a WHERE a.createdDate > :currentTimeMinus12Hours ORDER BY a.createdDate")
    Page<Post> findAllWithCreationDateTimeInLastTwelveHours(Pageable pageable);

}
