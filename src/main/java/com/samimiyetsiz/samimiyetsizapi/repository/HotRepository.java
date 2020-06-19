package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Hot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotRepository extends JpaRepository<Hot, Long> {
}
