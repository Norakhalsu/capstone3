package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.DTO.ComparisonResultDTO;
import com.example.capstone3ee.Model.*;
import com.example.capstone3ee.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;
    private final RatingRepository ratingRepository;
    private RequestRepository requestRepository;

    // GET
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    // ADD
    public void addUser(User user) {

        userRepository.save(user);
    }

    // UPDATE
    public void updateUser(Integer id, User updatedUser) {
        User user =userRepository.findUserByUsersId(id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setBio(updatedUser.getBio());
        user.setInterests(updatedUser.getInterests());
        user.setCareerGoals(updatedUser.getCareerGoals());
        user.setEvaluationResult(updatedUser.getEvaluationResult());
        user.setRole(updatedUser.getRole());

         userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserByUsersId(id);
        if (user==null){
            throw new ApiException("user not found");
        }
        userRepository.deleteById(id);
    }
// -------------------------------------- end point ----------------------------------------------------

    // استشارة طلب : by nora
    public void createRequest(Integer userId, Integer expertId, String requestDescription) {
        User user = userRepository.findUserByUsersId(userId);
        Expert expert = expertRepository.findExpertByExpertId(expertId);

        if (user == null) {
            throw new ApiException("User not found");
        }
        if (expert == null) {
            throw new ApiException("Expert not found");
        }
        if (requestDescription == null || requestDescription.isEmpty()) {
            throw new ApiException("Request description must not be empty");
        }

        Request request = new Request();
        request.setUser(user);
        request.setExpert(expert);
        request.setRequestDescription(requestDescription);

        try {
            requestRepository.save(request);
        } catch (Exception e) {
            throw new ApiException("Error saving request: " + e.getMessage());
        }
    }


    // YARA COMPAIR 2 USERS
    public ComparisonResultDTO compareUsers(Integer userId1, Integer userId2) {
        User user1 = userRepository.findUserByUsersId(userId1);
        User user2 = userRepository.findUserByUsersId(userId2);

        if (user1 == null || user2 == null) {
            throw new ApiException("One or both users not found");
        }

        Resume resume1 = user1.getResume();
        Resume resume2 = user2.getResume();

        if (resume1 == null || resume2 == null) {
            throw new ApiException("One or both users do not have a resume");
        }

        ComparisonResultDTO comparisonResult = new ComparisonResultDTO();
        comparisonResult.setUserId1(userId1);
        comparisonResult.setUserId2(userId2);
        comparisonResult.setSkills1(resume1.getSkills());
        comparisonResult.setSkills2(resume2.getSkills());
        comparisonResult.setProjects1(resume1.getProjects());
        comparisonResult.setProjects2(resume2.getProjects());
        comparisonResult.setCertifications1(resume1.getCertification());
        comparisonResult.setCertifications2(resume2.getCertification());
        comparisonResult.setAwards1(resume1.getAward());
        comparisonResult.setAwards2(resume2.getAward());
        comparisonResult.setEducation1(resume1.getEducation());
        comparisonResult.setEducation2(resume2.getEducation());

        StringBuilder summary = new StringBuilder();
        summary.append("Comparison between User ").append(userId1).append(" and User ").append(userId2).append(":\n");

        if (resume1.getSkills().size() > resume2.getSkills().size()) {
            summary.append("User ").append(userId1).append(" has more skills.\n");
        } else if (resume1.getSkills().size() < resume2.getSkills().size()) {
            summary.append("User ").append(userId2).append(" has more skills.\n");
        } else {
            summary.append("Both users have the same number of skills.\n");
        }


        if (resume1.getProjects().size() > resume2.getProjects().size()) {
            summary.append("User ").append(userId1).append(" has more projects.\n");
        } else if (resume1.getProjects().size() < resume2.getProjects().size()) {
            summary.append("User ").append(userId2).append(" has more projects.\n");
        } else {
            summary.append("Both users have the same number of projects.\n");
        }

        if (resume1.getCertification().size() > resume2.getCertification().size()) {
            summary.append("User ").append(userId1).append(" has more certifications.\n");
        } else if (resume1.getCertification().size() < resume2.getCertification().size()) {
            summary.append("User ").append(userId2).append(" has more certifications.\n");
        } else {
            summary.append("Both users have the same number of certifications.\n");
        }

        if (resume1.getAward().size() > resume2.getAward().size()) {
            summary.append("User ").append(userId1).append(" has more awards.\n");
        } else if (resume1.getAward().size() < resume2.getAward().size()) {
            summary.append("User ").append(userId2).append(" has more awards.\n");
        } else {
            summary.append("Both users have the same number of awards.\n");
        }
        summary.append("Education comparison: ").append(userId1).append(" has ")
                .append(resume1.getEducation()).append(", while ")
                .append(userId2).append(" has ").append(resume2.getEducation()).append(".\n");

        comparisonResult.setComparisonSummary(summary.toString());

        return comparisonResult;
    }





}