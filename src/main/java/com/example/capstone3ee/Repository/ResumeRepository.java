package com.example.capstone3ee.Repository;


import com.example.capstone3ee.Model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    Resume findResumeByResumeId(Integer id);



}
