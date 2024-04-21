package com.example.unscape.repository;

import com.example.unscape.entity.auth.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<AccessEntity, Long> {
}
