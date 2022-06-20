package com.testserver.test.repository;

import com.testserver.test.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRep extends JpaRepository<Location,Long> {
}
