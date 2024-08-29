package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.DTO.ResumeDTO;
import com.example.capstone3ee.Model.Resume;
import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Repository.ResumeRepository;
import com.example.capstone3ee.Repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    // GET all resumes
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // ADD a new resume
    public void addResume(ResumeDTO resumeDTO) {
        User user = userRepository.findUserByUsersId(resumeDTO.getUserId());
        if (user == null){
           throw new ApiException("User not found");
        }
        Resume resume = new Resume(null,resumeDTO.getContent(),resumeDTO.getSkills(),resumeDTO.getProjects(),resumeDTO.getCertification(),resumeDTO.getAward(),resumeDTO.getEducation(),resumeDTO.getResumeRating() ,user);
        resumeRepository.save(resume);
    }




    // UPDATE an existing resume
    public Resume updateResume(ResumeDTO resumeDTO) {
        Resume resume = resumeRepository.findResumeByResumeId(resumeDTO.getUserId());
        if (resume == null){
           throw  new ApiException("User not found");
        }
        resume.setContent(resumeDTO.getContent());
        resume.setSkills(resumeDTO.getSkills());
        resume.setProjects(resumeDTO.getProjects());
        resume.setCertification(resumeDTO.getCertification());
        resume.setAward(resumeDTO.getAward());
        resume.setEducation(resumeDTO.getEducation());
        return resumeRepository.save(resume);
    }

    public void deleteResume(Integer id) {
        Resume resume = resumeRepository.findResumeByResumeId(id);
        if (resume == null) {
            throw new ApiException("Resume not found");
        }
        resumeRepository.delete(resume);
    }


}