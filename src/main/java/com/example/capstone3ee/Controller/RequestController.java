package com.example.capstone3ee.Controller;


import com.example.capstone3ee.Model.Request;
import com.example.capstone3ee.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    // GET all requests
    @GetMapping("/get")
    public ResponseEntity getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return  ResponseEntity.status(200).body(requests);
    }

    // ADD a new request
    @PostMapping("/add/{expertId}/{userId}")
    public ResponseEntity addRequest(@PathVariable Integer expertId , @PathVariable Integer userId ,@Valid @RequestBody Request request) {
        requestService.addRequest(expertId,userId,request);
        return ResponseEntity.status(200).body("Request added successfully");
    }

    @PutMapping("/update/{expertId}/{byExpert}/{requestId}/{status}")
    public ResponseEntity updateRequest(@PathVariable Integer expertId,@PathVariable String byExpert, @PathVariable Integer requestId,@PathVariable String status) {
        requestService.updateRequest(expertId, byExpert, requestId,status);
        return ResponseEntity.status(200).body("Request updated Successfully");
    }


    // DELETE a request
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRequest(@PathVariable Integer id) {
        requestService.deleteRequest(id);
        return  ResponseEntity.status(200).body("Request deleted Successfully");
    }

     // ----------------------------------------- end point ---------------------------
     @GetMapping("/{expertId}/activeRequests")
     public List<Request> getActiveRequestsForExpert(@PathVariable Integer expertId) {
         return requestService.getActiveRequestsForExpert(expertId);
      }
  //     /api/v1/request/1/activeRequests

// {
//  "requestDescription": "Urgent request for assistance"   }


     }
