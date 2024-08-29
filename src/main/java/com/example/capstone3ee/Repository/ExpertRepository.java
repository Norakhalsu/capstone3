package com.example.capstone3ee.Repository;


import com.example.capstone3ee.Model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    Expert findExpertByExpertId(Integer id);
    List<Expert> findExpertBySkills(String skill);

}
