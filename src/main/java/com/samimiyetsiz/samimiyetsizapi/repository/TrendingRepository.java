package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendingRepository extends JpaRepository<Trending, Long> {
}
