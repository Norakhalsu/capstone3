package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findRequestByReqId(Integer id);

    Request findByExpertAndStatus(Expert expert, String status);
    Request findRequestByExpert(Expert expert);
}
