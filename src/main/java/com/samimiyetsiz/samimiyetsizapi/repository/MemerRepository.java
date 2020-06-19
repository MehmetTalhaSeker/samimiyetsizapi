package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Memer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemerRepository extends JpaRepository<Memer, Long> {

    Optional<Memer> findOneByUsername(String username);

    Optional<Memer> findOneByEmail(String email); 

    Optional<Memer> findOneWithAuthoritiesByUsername(String username);

    Optional<Memer> findOneWithAuthoritiesByEmailIgnoreCase(String email);
}
