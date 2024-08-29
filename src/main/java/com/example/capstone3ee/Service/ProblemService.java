package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.Assessment;
import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.Problem;
import com.example.capstone3ee.Repository.AssessmentRepository;
import com.example.capstone3ee.Repository.ExpertRepository;
import com.example.capstone3ee.Repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProblemService {
//YARA
    private final ProblemRepository problemRepository;
    private final AssessmentRepository assessmentRepository;
    private final ExpertRepository expertRepository;

    public void sendProblemToExpert(Integer expertId, Integer assessmentId, String question, String problem) {
        Expert expert = expertRepository.findExpertByExpertId(expertId);
        Assessment assessment = assessmentRepository.findAssessmentByAssessmentId(assessmentId);

        if (expert == null) {
            throw new ApiException("Expert not found");
        }

        if (assessment == null) {
            throw new ApiException("Assessment not found");
        }

        Problem newProblem = new Problem();
        newProblem.setQuestion(question);
        newProblem.setProblem(problem);
        newProblem.setStatus("ACTIVE");
        newProblem.setAssessment(assessment);
        newProblem.setExpert(expert);

        problemRepository.save(newProblem);
    }

    public List<Problem> getActiveProblems() {
        return problemRepository.findByStatus("ACTIVE");
    }

    public Problem getProblemById(Integer id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new ApiException("Problem not found"));
    }

    public void deleteProblem(Integer id) {
        if (!problemRepository.existsById(id)) {
            throw new ApiException("Problem not found");
        }
        problemRepository.deleteById(id);
    }

    public void respondToProblem(Integer expertId, Integer problemId, String response) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ApiException("Problem not found"));
        Expert expert = expertRepository.findExpertByExpertId(expertId);

        if (expert == null || !expert.getExpertId().equals(problem.getExpert().getExpertId())) {
            throw new ApiException("Expert not authorized or does not exist");
        }

        problem.setResponse(response);
        problem.setStatus("CLOSED");

        problemRepository.save(problem);
    }
}
