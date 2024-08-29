package com.example.capstone3ee.Controller;

import com.example.capstone3ee.DTO.ResumeDTO;
import com.example.capstone3ee.Model.Resume;
import com.example.capstone3ee.Service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // GET all resumes
    @GetMapping("/get")
    public ResponseEntity getAllResumes() {
        List<Resume> resumes = resumeService.getAllResumes();
        return  ResponseEntity.status(200).body(resumes);
    }

    // ADD a new resume
    @PostMapping("/add")
    public ResponseEntity addResume( @Valid @RequestBody ResumeDTO resumeDTO) {
        resumeService.addResume(resumeDTO);
        return ResponseEntity.status(201).body("resume added successfully");
    }

    // UPDATE an existing resume's user
    @PutMapping("/update")
    public ResponseEntity updateResume( @Valid @RequestBody ResumeDTO resumeDTO) {
        Resume resume = resumeService.updateResume(resumeDTO);
        return ResponseEntity.status(201).body("resume updated successfully");
    }

    // DELETE a resume
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteResume(@PathVariable Integer id) {
        resumeService.deleteResume(id);
        return ResponseEntity.status(201).body("resume deleted successfully");
    }

}