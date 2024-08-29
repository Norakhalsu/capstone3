package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.DTO.FeedBackDTO;
import com.example.capstone3ee.Model.*;
import com.example.capstone3ee.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final FeedBackRepository feedbackRepository;
    private final RequestRepository requestRepository;
    private final GroupsRepository groupsRepository;
    private final UserRepository userRepository;

    // GET
    public List<FeedBack> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // ADD
    public void addFeedback(FeedBackDTO feedBackDTO) {
        Request request=requestRepository.findRequestByReqId(feedBackDTO.getRequestId());
        if (request==null){
            throw new ApiException("Request not found");
        }
        FeedBack feedBack=new FeedBack(null,feedBackDTO.getFeedbackText(),request);
        feedbackRepository.save(feedBack);
    }

    // UPDATE
    public void updateFeedback(FeedBackDTO feedBackDTO) {
        FeedBack feedBack = feedbackRepository.findFeedBackByFeedbackId(feedBackDTO.getRequestId());
        if (feedBack == null) {
            throw new RuntimeException("Feedback not found");
        }
        feedBack.setFeedbackText(feedBackDTO.getFeedbackText());
        feedbackRepository.save(feedBack);
    }

    // DELETE
    public void deleteFeedback(Integer id) {
        FeedBack feedback = feedbackRepository.findFeedBackByFeedbackId(id);
        if (feedback == null) {
            throw new RuntimeException("Feedback not found");
        }
        feedbackRepository.deleteById(id);
    }

// ------------------------------- end points ----------------------------------
    // show feed back of request : by nora
    public String getFeedbackForRequest(Integer requestId) {
        Request feedbackRequest = requestRepository.findRequestByReqId(requestId);

        if (feedbackRequest == null) {
            throw new RuntimeException("Request not found");
        }

        FeedBack feedback = feedbackRepository.findFeedBackByRequestReqId(requestId);
        if (feedback == null) {
            throw new ApiException("No feedback available for this request.");
        }
        return feedback.getFeedbackText();
    }




    // ADD Feedback by Expert for a specific Request : YARA
    public void addFeedbackByExpert(Integer expertId, Integer userId, Integer requestId, String feedbackText) {
        // Find the request by ID
        Request request = requestRepository.findRequestByReqId(requestId);
        if (request == null) {
            throw new ApiException("Request not found");
        }

        if (!request.getExpert().getExpertId().equals(expertId)) {
            throw new ApiException("Expert is not associated with this request");
        }

        if (!request.getUser().getUsersId().equals(userId)) {
            throw new ApiException("User is not associated with this request");
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setFeedbackText(feedbackText);
        feedBack.setRequest(request);
        feedbackRepository.save(feedBack);
        request.setStatus("COMPLETED");
        requestRepository.save(request);
    }




}