package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.*;
import com.example.capstone3ee.Repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Group;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service @RequiredArgsConstructor
public class GroupsService {
    private final GroupsRepository groupsRepository;
    private final UserRepository userRepository;
    private final GroupInvitationRepository groupInvitationRepository;
    private final ExpertRepository expertRepository;
    private final TaskRepository taskRepository;
    //  private final ExpertRepository expertRepository;


    public List<Groups> getAllGroup() {
        return groupsRepository.findAll();
    }


//    public void addGroup(Groups group) {
//        groupsRepository.save(group);
//    }


        public void addGroup(Integer userId, Groups group) {
       User user = userRepository.findUserByUsersId(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        group.setLeader(user);
        groupsRepository.save(group);
    }


        public void updateGroup(Integer id ,Groups group) {
        Groups group1=groupsRepository.findGroupByGroupId(id);

        if(group1==null) {
            throw new ApiException("group not found");
        }
        group1.setName(group.getName());
        group1.setRequiredSkills(group.getRequiredSkills());
        group1.setApplicable(group.isApplicable());
        group1.setProjects(group.getProjects());
        group1.setMembers(group.getMembers());
        groupsRepository.save(group);
    }


    public void deleteGroup(Integer id) {
        Groups group=groupsRepository.findGroupByGroupId(id);
        if(group==null) {
            throw new ApiException("group not found");
        }
        groupsRepository.delete(group);
    }

//    public void updateGroup(Integer id, Groups group) {
//        Groups group1 = groupsRepository.findGroupByGroupId(id);
//
//        if (group1 == null) {
//            throw new ApiException("group not found");
//        }
//        group1.setName(group.getName());
//        group1.setDescription(group.getDescription());
//        group1.setApplicable(group.isApplicable());
//        group1.setProjects(group.getProjects());
//        group1.setMembers(group.getMembers());
//        groupsRepository.save(group);
//    }




//    public void deleteGroup(Integer id) {
//        Groups group = groupsRepository.findGroupByGroupId(id);
//        if (group == null) {
//            throw new ApiException("group not found");
//        }
//        groupsRepository.delete(group);
//    }

    //List<Integer> // assign users to group
    public void assignUsersToGroups(Integer groupId, Integer userIds) {
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        User users = userRepository.findUserByUsersId(userIds);
        if (group == null) {
            throw new ApiException("group not found");
        }
        if (users == null) {
            throw new ApiException("user not found");
        }
        group.getUsers().add(users);
        users.getGroups().add(group);
        userRepository.save(users);
        groupsRepository.save(group);
    }


    // -------------------------------------  End point ---------------------------------------
    // remove members from group  : by Nora
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


    // assign task to group by Nora
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

    // get group tasks
    public List<Tasks> groupTasks(Integer groupId) {
        // Retrieve the group by ID
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        if (group == null) {
            throw new ApiException("Group not found");
        }
        List<Tasks> tasks = new ArrayList<>(group.getTasks());
        return tasks;
    }



    // تعيين تقرير أداء الفريق : by nora
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


//
//    //List<Integer> // assign users to group
//    public void assignUsersToGroups(Integer groupId, Integer userIds) {
//        Groups group=groupsRepository.findGroupByGroupId(groupId);
//        User users=userRepository.findUserByUsersId(userIds);
//        if(group==null) {
//            throw new ApiException("group not found");
//        }
//        if(users==null) {
//            throw new ApiException("user not found");
//        }
//        group.getUsers().add(users);
//        users.getGroups().add(group);
//        userRepository.save(users);
//        groupsRepository.save(group);
//
//
//    }


//    public void addMemberToGroup(Integer groupId, Integer userId) {
//        Groups group = groupsRepository.findById(groupId)
//                .orElseThrow(() -> new ApiException("Group not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ApiException("User not found"));
//
//        group.getMembers().add(user);
//        groupsRepository.save(group);
//    }


//     // by mohammad
//    public void inviteUserToGroup(Integer groupId, Integer senderId, Integer receiverId) {
//        Groups group = groupsRepository.findGroupByGroupId(groupId);
//        User sender = userRepository.findUserByUsersId(senderId);
//        User receiver = userRepository.findUserByUsersId(receiverId);
//
//        if (group == null) throw new ApiException("Group not found");
//        if (sender == null) throw new ApiException("Sender not found");
//        if (receiver == null) throw new ApiException("Receiver not found");
//
//        GroupInvitation invitation = new GroupInvitation();
//        invitation.setGroup(group);
//        invitation.setSender(sender);
//        invitation.setReceiver(receiver);
//        invitation.setStatus("PENDING");
//
//        groupInvitationRepository.save(invitation);
//    }
//
//
//
//    // mohammad
//    public void acceptInvitation(Integer invitationId) {
//        GroupInvitation invitation = groupInvitationRepository.findById(invitationId)
//                .orElseThrow(() -> new ApiException("Invitation not found"));
//
//        invitation.setStatus("ACCEPTED");
//        groupInvitationRepository.save(invitation);
//
//        Groups group = invitation.getGroup();
//        User receiver = invitation.getReceiver();
//
//        group.getMembers().add(receiver);
//        groupsRepository.save(group);
//
//        receiver.getGroups().add(group);
//        userRepository.save(receiver);
//    }
//
//
//     //mohammad
//    public List<User> getGroupMembers(Integer groupId) {
//        Groups group = groupsRepository.findById(groupId)
//                .orElseThrow(() -> new ApiException("Group not found"));
//        return group.getMembers();
//    }
//
//// mohammad
//    public void declineInvitation(Integer invitationId) {
//        GroupInvitation invitation = groupInvitationRepository.findById(invitationId)
//                .orElseThrow(() -> new ApiException("Invitation not found"));
//
//        invitation.setStatus("DECLINED");
//        groupInvitationRepository.save(invitation);
//    }
//
//    // mohammad
//    public List<GroupInvitation> getPendingInvitationsForUser(Integer userId) {
//        return groupInvitationRepository.findByReceiverIdAndStatus(userId, "PENDING");
//    }
//// mohammad
//    public List<GroupInvitation> getSentInvitationsByLeader(Integer leaderId) {
//        return groupInvitationRepository.findBySenderId(leaderId);
//    }
//// mohammad
//    public void transferLeadership(Integer groupId, Integer newLeaderId) {
//        Groups group = groupsRepository.findById(groupId)
//                .orElseThrow(() -> new RuntimeException("Group not found"));
//        User newLeader = userRepository.findById(newLeaderId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        group.setLeader(newLeader);
//        groupsRepository.save(group);
//    }
//













//        public void declineGroupInvite(Integer groupId) {
//           Groups groups = groupsRepository.findGroupByGroupId(groupId);
//           if(groups == null){
//               throw new ApiException("group not found");
//           } System.out.println("Invitation declined for group with ID: " + groupId);
//        }


}