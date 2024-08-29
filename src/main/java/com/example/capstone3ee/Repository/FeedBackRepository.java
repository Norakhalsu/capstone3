package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    FeedBack findFeedBackByFeedbackId(Integer id);

    FeedBack findFeedBackByRequestReqId(Integer requestId);
}
