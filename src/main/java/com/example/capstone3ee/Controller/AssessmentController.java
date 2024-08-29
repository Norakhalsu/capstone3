package com.example.capstone3ee.Controller;


import com.example.capstone3ee.Model.Assessment;
import com.example.capstone3ee.Service.AssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/assessment")
public class AssessmentController {
    private final AssessmentService assessmentService;

    @GetMapping("/get")
    public ResponseEntity getAssessment() {
        return ResponseEntity.status(200).body(assessmentService.getAllAssessments());
    }

    @PostMapping("/add/{expId}/{userId}")
    public ResponseEntity addAssessment(@PathVariable Integer expId,@PathVariable Integer userId,@Valid @RequestBody Assessment assessment) {
        assessmentService.addAssessment(expId,userId,assessment);
        return ResponseEntity.status(200).body("assessment added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAssessment(@PathVariable int id, @Valid @RequestBody Assessment assessment) {
        assessmentService.updateAssessment(id, assessment);
        return ResponseEntity.status(200).body("assessment updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAssessment(@PathVariable int id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.status(200).body("assessment deleted successfully");
    }

    // -------------------------------------------- end pint -------------------------------
    @PostMapping("/submit/{assessmentId}/{userId}")// yara
    public ResponseEntity submitAnswers(@PathVariable Integer assessmentId, @PathVariable Integer userId, @Valid @RequestBody List<String> answers) {
        assessmentService.submitAnswers(assessmentId, userId, answers);
        return ResponseEntity.status(200).body("Answers submitted successfully");
    }








//    @PutMapping("/{assementtId}/assign/{userId}")
//    public ResponseEntity assignAssement(@PathVariable int assementtId,  @PathVariable Integer userId) {
//        assessmentService.a(assementtId, userId);
//        return ResponseEntity.status(200).body("assessment assigned successfully");
//    }


}
