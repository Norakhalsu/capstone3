package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.Assessment;
import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Repository.AssessmentRepository;
import com.example.capstone3ee.Repository.ExpertRepository;
import com.example.capstone3ee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;
    private  final ExpertRepository expertRepository;

    public List<Assessment> getAllAssessments() {
       return  assessmentRepository.findAll();
    }



    //  يتاكد ان اليوزر موجود ويضيفها مباشرة لليوزر.... او نعمل ميثود assign ....واليوزر اي دي بالمودل assessment
    public void addAssessment(Integer expertId,Integer userId,Assessment assessment) {
        User user =userRepository.findUserByUsersId(userId);
        Expert expert =expertRepository.findExpertByExpertId(expertId);
        if(user==null || expert==null) {
            throw new ApiException("Expert or User not found");
        }
        assessment.setUser(user);
        assessment.setExpert(expert);
        assessmentRepository.save(assessment);
    }


    public void updateAssessment(Integer id, Assessment assessment) {
        Assessment assessment1 = assessmentRepository.findAssessmentByAssessmentId(id);
        if (assessment1 == null) {
            throw new ApiException("Assessment not found");
        }
        assessment1.setAssessmentType(assessment.getAssessmentType());
        assessment1.setScore(assessment.getScore());
        assessment1.setAnswer(assessment.getAnswer());
        assessment1.setQuestions(assessment.getQuestions());
        assessment1.setDateTaken(assessment.getDateTaken());
        assessmentRepository.save(assessment1);
    }

    public void deleteAssessment(Integer id) {
        Assessment assessment=assessmentRepository.findAssessmentByAssessmentId(id);
        if(assessment==null) {
            throw new ApiException("assessment not found");
        }
        assessmentRepository.delete(assessment);
    }

    // ----------------------------------------------- end pint -----------------------------------

                //YARA // ADD ANSWERS
    public void submitAnswers(Integer assessmentId, Integer userId, List<String> answers) {
        Assessment assessment = assessmentRepository.findAssessmentByAssessmentId(assessmentId);
        User user = userRepository.findUserByUsersId(userId);

        if (assessment == null || user == null || !assessment.getUser().getUsersId().equals(userId)) {
            throw new ApiException("Assessment or user not found, or user not assigned to this assessment");
        }

        assessment.setAnswer(answers);
        assessmentRepository.save(assessment);
    }

}
