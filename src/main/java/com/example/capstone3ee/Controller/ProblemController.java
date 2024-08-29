package com.example.capstone3ee.Controller;

import com.example.capstone3ee.Model.Problem;
import com.example.capstone3ee.Service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/problem")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/expert/{expertId}/assessment/{assessmentId}/report")
    public ResponseEntity<String> reportProblem(@PathVariable Integer expertId, @PathVariable Integer assessmentId, @RequestBody Problem problem) {
        problemService.sendProblemToExpert(expertId, assessmentId, problem.getQuestion(), problem.getProblem());
        return ResponseEntity.status(200).body("Problem reported successfully");
    }

    @GetMapping("/active")
    public ResponseEntity getActiveProblems() {
        List<Problem> activeProblems = problemService.getActiveProblems();
        return ResponseEntity.ok(activeProblems);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable Integer id) {
        Problem problem = problemService.getProblemById(id);
        return ResponseEntity.ok(problem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProblem(@PathVariable Integer id) {
        problemService.deleteProblem(id);
        return ResponseEntity.status(200).body("Problem deleted successfully");
    }

    @PutMapping("/expert/{expertId}/problem/{problemId}/response")
    public ResponseEntity respondToProblem(@PathVariable Integer expertId, @PathVariable Integer problemId, @RequestBody String response) {
        problemService.respondToProblem(expertId, problemId, response);
        return ResponseEntity.status(200).body("Problem closed and response sent successfully");
    }
}
