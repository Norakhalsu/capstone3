package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.Request;
import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Repository.ExpertRepository;
import com.example.capstone3ee.Repository.RequestRepository;
import com.example.capstone3ee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;

    // GET
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // must check if expert and user is exisit in system
    public void addRequest(Integer expertId,Integer userId,Request request) {
        Expert expert=expertRepository.findExpertByExpertId(expertId);
        User user=userRepository.findUserByUsersId(userId);
        if (expert==null) {
            throw new ApiException("expert is null");
        }
        if (user==null) {
            throw new ApiException("user is null");
        }
        request.setStatus("pending");
        request.setExpert(expert);
        request.setUser(user);
        requestRepository.save(request);
    }


    // UPDATE
    public Request updateRequest(Integer expertId , String byExpert,Integer requestId , String status) {
        Request request1 = requestRepository.findRequestByReqId(requestId);
        Expert expert=expertRepository.findExpertByExpertId(expertId);
        if (request1==null) {
            throw new ApiException("request is null");
        }
        if (expert==null) {
            throw new ApiException("expert is null");
        }
       // request1.setExpertId(request.getExpertId());
        if (byExpert.equals(status)) {
            request1.setStatus("Accepted");
        }
        else if(byExpert.equalsIgnoreCase(status)) {
            request1.setStatus("Declined");
        }
        else {
            throw new ApiException("invalid input status");
        }
        return requestRepository.save(request1);
    }



    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }

     // ------------------------------- end point ----------------------



    // by nora
    public List<Request> getActiveRequestsForExpert(Integer expertId) {
        Expert expert = expertRepository.findExpertByExpertId(expertId);
        List<Request> activeList=(List<Request>) requestRepository.findByExpertAndStatus(expert, "active");

        if (expert == null) {
            throw new ApiException("Expert not found");
        }
        if (activeList==null) {
            throw new ApiException("active list is null");
        }
        return (List<Request>) requestRepository.findByExpertAndStatus(expert, "active");
    }




}
