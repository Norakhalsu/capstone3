package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    Assessment findAssessmentByAssessmentId(Integer id);

}
