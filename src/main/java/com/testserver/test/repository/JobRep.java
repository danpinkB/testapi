package com.testserver.test.repository;

import com.testserver.test.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRep extends JpaRepository<Job,Long> {
}
