package com.example.capstone3ee.Service;


import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.Request;
import com.example.capstone3ee.Model.Resume;
import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Repository.ExpertRepository;
import com.example.capstone3ee.Repository.RequestRepository;
import com.example.capstone3ee.Repository.ResumeRepository;
import com.example.capstone3ee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertRepository expertRepository;
    private final RequestRepository requestRepository;
   private final UserRepository userRepository;
   private final ResumeRepository resumeRepository;


    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    //YARA
    public void addExpert(Expert expert) {
        expert.setStatus("non-active");
        expert.setAvailability("no");
        expertRepository.save(expert);
    }


    public void updateExpert(Integer id, Expert expert) {
        Expert updateExpert = expertRepository.findExpertByExpertId(id);

        if (updateExpert == null) {
            throw new ApiException("Expert not found");
        }

        //updateExpert.setUserId(expert.getUserId());
        updateExpert.setExpertiseFailed(expert.getExpertiseFailed());
        updateExpert.setQualificationDegree(expert.getQualificationDegree());
        updateExpert.setRatings(expert.getRatings());
        updateExpert.setAvailability(expert.getAvailability());
        //updateExpert.setExpertiseList(expert.getExpertiseList());
        //updateExpert.setStatus(expert.getStatus());

        expertRepository.save(updateExpert);
    }

    public void deleteExpert(Integer id) {
        Expert deleteExpert = expertRepository.findExpertByExpertId(id);

        if (deleteExpert == null) {
            throw new ApiException("Expert not found");
        }

        expertRepository.delete(deleteExpert);
    }

    // --------------------------------------------- end point ---------------------------
      // by nora
    public List<Expert> findExpertsBySkills(String skills) {
       List<Expert> experts =expertRepository.findExpertBySkills(skills);
       if (experts == null) {
           throw new ApiException("Expert not found with skills");
       }
        return expertRepository.findExpertBySkills(skills);
    }



    // تصفية قائمة الخبراء بناءً على اسم التخصص وتجنب تكرار الخبراء بناءً على الاسم  : by nora
    public List<Expert> filterExpertsBySpecialty(String major) {
        List<Expert> experts = expertRepository.findAll();
        Set<String> seen = new HashSet<>();
        List<Expert> filteredExperts = new ArrayList<>();
        if (experts != null && !experts.isEmpty()) {
            for (Expert expert : experts) {
                if (expert.getMajor().equals(major) && !seen.contains(expert.getFullName())) {
                    filteredExperts.add(expert);
                    seen.add(expert.getFullName());
                }
            }
        }
        return filteredExperts;
    }




    //YARA :تقييم السيرة الذاتيه
    public void evaluateResume(Integer expertId, Integer userId, Integer rate) {
        Expert expert = expertRepository.findExpertByExpertId(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }

        User user = userRepository.findUserByUsersId(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        Resume resume = resumeRepository.findResumeByResumeId(userId);
        if (resume == null) {
            throw new ApiException("Resume not found for user");
        }

        String evaluationCV;
        if (rate == null) {
            throw new ApiException("Rate is required"); // Handle the case where rate is null
        } else if (rate == 1) {
            evaluationCV = "Expert " + expert.getExpertId() + " evaluated the resume: Looks good!";
        } else if (rate == 0) {
            evaluationCV = "Expert " + expert.getExpertId() + " evaluated the resume: Needs more updates.";
        } else {
            throw new ApiException("Invalid rate value. It should be either 0 or 1.");
        }
        user.setEvaluationCV(evaluationCV);
        userRepository.save(user);
    }



    //YARA: check if the expert is avaliable
    public String getExpertAvailability(Integer expertId) {
        Expert expert = expertRepository.findExpertByExpertId(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }

        return expert.getAvailability();
    }



    //YARA : ACTIVATE EXPERT
    public void activeExpert(Integer expertId, Integer userId) {
        Expert expert = expertRepository.findExpertByExpertId(expertId);
        User user = userRepository.findUserByUsersId(userId);
        if (user == null || expert == null) {
            throw new ApiException("Expert not found");
        }
        if (user.getRole().equals("admin")) {
            expert.setStatus("active");
            expert.setAvailability("yes");
            expertRepository.save(expert);
        }
    }

//
//    public List<Expert> matchUsersToExperts() {
//        List<Resume> resumes = resumeRepository.findAll();
//        if (resumes == null || resumes.isEmpty()) {
//            throw new ApiException("Resumes not found");
//        }
//        List<Expert> matchedExperts = new ArrayList<>();
//        for (Resume resume : resumes) {
//            if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
//               // String[] skillsArray = resume.getSkills(); // Corrected method to split the skills
//                List<Expert> experts = findExpertsBySkills(resume.getSkills());
//                if (!experts.isEmpty()) {
//                    matchedExperts.add(experts.get(0)); // Add the first matched expert for simplicity
//                } }}
//        return matchedExperts;
//    }






}


