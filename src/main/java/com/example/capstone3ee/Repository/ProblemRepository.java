package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    Problem findProblemById(Integer id);
    List<Problem> findByStatus(String status);
}
