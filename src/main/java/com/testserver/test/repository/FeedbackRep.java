package com.testserver.test.repository;

import com.testserver.test.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRep extends JpaRepository<Feedback,Long> {
}
