package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Role;
import com.samimiyetsiz.samimiyetsizapi.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName rolename);

}
