package com.example.capstone3ee.Controller;


import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Service.ExpertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expert")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertService expertService;

    @GetMapping("/get")
    public ResponseEntity getAllExperts() {
        return ResponseEntity.status(200).body(expertService.getAllExperts());
    }

    @PostMapping("/add")
    public ResponseEntity addExpert(@Valid @RequestBody Expert expert) {
        expertService.addExpert(expert);
        return ResponseEntity.status(200).body("Expert added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateExpert(@Valid @RequestBody Expert expert, @PathVariable Integer id) {
        expertService.updateExpert(id, expert);
        return ResponseEntity.status(200).body("Expert updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteExpert(@PathVariable Integer id) {
        expertService.deleteExpert(id);
        return ResponseEntity.status(200).body("Expert deleted successfully");
    }

  // ------------------------------------------------- end point --------------------------------


    @PostMapping("/{expertId}/{userId}/resume/{rate}")// Yara
    public ResponseEntity evaluateResume(@PathVariable Integer expertId, @PathVariable Integer userId, @PathVariable Integer rate) {
        expertService.evaluateResume(expertId, userId, rate);
        return ResponseEntity.status(200).body("Resume evaluated successfully by expert.");
    }

    @GetMapping("/{expertId}/availability")// Yara
    public ResponseEntity getExpertAvailability(@PathVariable Integer expertId) {
        String availability = expertService.getExpertAvailability(expertId);
        return ResponseEntity.ok(availability);
    }
    @PostMapping("/active/{expertId}/{userId}")//Yara
    public ResponseEntity activateExpert(@PathVariable Integer expertId, @PathVariable Integer userId) {
        expertService.activeExpert(expertId, userId);
        return ResponseEntity.status(200).body("Expert activated successfully");
    }


// ----------------------- end point by nora
  @GetMapping("/search/{skills}")//Nora
  public ResponseEntity<List<Expert> >searchExpertsBySkills(@PathVariable String skills) {
      return ResponseEntity.status(200).body(expertService.findExpertsBySkills(skills));
  }
    @GetMapping("/experts/{major}")// Nora
    public ResponseEntity<List<Expert>> getExpertsByMajor(@PathVariable String major) {
        return ResponseEntity.status(200).body(expertService.filterExpertsBySpecialty(major));
    }
  // /api/v1/expert/experts/networks



//    @GetMapping("/matchSkills")
//    public ResponseEntity<List<Expert>> matchUsersToExperts() {
//        return ResponseEntity.status(200).body(expertService.matchUsersToExperts());
//    }





}