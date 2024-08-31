1- class Assessment  
2-class Resume 
3-c;ass Events 
5-class Tasks

----------------------- End Point ----------------------
1- 
  public String calculateEventDuration(Integer eventId) {
        Events event = eventsRepository.findEventsById(eventId);

        if (event.getStartDate() != null && event.getEndDate() != null) {
            LocalDateTime startDateTime = event.getStartDate().atStartOfDay();
            LocalDateTime endDateTime = event.getEndDate().atStartOfDay();

            Duration duration = Duration.between(startDateTime, endDateTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60; // Remaining minutes after hours

            return String.format("Duration: %d hours and %d minutes", hours, minutes);
        } else {
            return "Duration: 0 hours and 0 minutes"; // or handle as needed
        }

        
2-
    public void assignTaskToGroup(Integer groupId, String type, Tasks tasks) {
        Groups group = groupsRepository.findById(groupId)
                .orElseThrow(() -> new ApiException("Group not found"));

        if (!group.getGroupType().equals(type)) {
            throw new ApiException("Task does not match group domain");
        }
        if (group.getTasks() == null) {
            group.setTasks(new HashSet<>());
        }
        Tasks task = new Tasks();
        task.setTaskName(tasks.getTaskName());
        task.setDueDate(tasks.getDueDate());
        task.setDetails(tasks.getDetails());
        task.getGroups().add(group); // Assuming Tasks has a Set<Groups> groups
        group.getTasks().add(task);
        groupsRepository.save(group);
    }

3-
   public List<Tasks> groupTasks(Integer groupId) {
        // Retrieve the group by ID
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        if (group == null) {
            throw new ApiException("Group not found");
        }
        List<Tasks> tasks = new ArrayList<>(group.getTasks());
        return tasks;
    }

4-
     public String assignTeamPerformanceReport(Integer groupId, Integer taskId, int ratingTeamPerform) {
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        Tasks task = taskRepository.findTaskByTaskId(taskId);

        if (group == null) {
            throw new ApiException("Group not found");
        }
        if (task == null) {
            throw new ApiException("Task not found");
        }
        if (group.getTasks().isEmpty()) {
            throw new ApiException("Tasks not found in the group");
        }
        if (group.getTeamPerformance() != 0) {
            throw new ApiException("Team performance rating already assigned for this group");
        }
        group.setTeamPerformance(ratingTeamPerform);
        task.setTeamPerformanceRating(ratingTeamPerform);
        groupsRepository.save(group);
        taskRepository.save(task);
        return "Team performance report assigned successfully.";
    }
5-
 public List<Expert> filterExpertsBySpecialty(String major) {
        List<Expert> experts = expertRepository.findAll();
        Set<String> seen = new HashSet<>();
        List<Expert> filteredExperts = new ArrayList<>();
        if (experts != null && !experts.isEmpty()) {
            for (Expert expert : experts) {
                if (expert.getMajor().equals(major) && !seen.contains(expert.getFullName())) {
                    filteredExperts.add(expert);
                    seen.add(expert.getFullName());
                }
            }
        }
        return filteredExperts;
    }

    
6-
  public List<Expert> findExpertsBySkills(String skills) {
       List<Expert> experts =expertRepository.findExpertBySkills(skills);
       if (experts == null) {
           throw new ApiException("Expert not found with skills");
       }
        return expertRepository.findExpertBySkills(skills);
    }

    
    
7-
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

8-
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

9-
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

10-
  public void removeMember(Integer groupId, Integer userId) {
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        User user = userRepository.findUserByUsersId(userId);

        if (group == null) {
            throw new ApiException("group not found");
        }
        if (user == null || !group.getMembers().contains(user)) {
            throw new ApiException("User not found in the group");
        }
        if (user.getRole().equals("admin")) {
            group.getMembers().remove(user);
        } else {
            throw new ApiException("User is not an admin");
        }
        groupsRepository.save(group);
    }

    

