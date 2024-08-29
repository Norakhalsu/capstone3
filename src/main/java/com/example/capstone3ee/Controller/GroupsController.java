package com.example.capstone3ee.Controller;

import com.example.capstone3ee.Model.Groups;
import com.example.capstone3ee.Model.Tasks;
import com.example.capstone3ee.Service.GroupsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupsController {
 private final GroupsService groupsService;


        @GetMapping("/get")
        public ResponseEntity getAllGroups() {
            return ResponseEntity.status(200).body(groupsService.getAllGroup());
        }


        @PostMapping("/add/{userId}")
        public ResponseEntity addGroup(@PathVariable Integer userId,@Valid @RequestBody Groups group) {
            groupsService.addGroup(userId,group);
            return ResponseEntity.status(200).body("group added successfully");
        }

        @PutMapping("/update/{id}")
        public ResponseEntity updateGroup(@PathVariable int id, @Valid @RequestBody Groups group) {
            groupsService.updateGroup(id, group);
            return ResponseEntity.status(200).body("group updated successfully");
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteGroup(@PathVariable int id) {
            groupsService.deleteGroup(id);
            return ResponseEntity.status(200).body("group deleted successfully");
        }

     @PutMapping("/assign/{groupId}/{userId}")
    public ResponseEntity assignGroup(@PathVariable int groupId, @PathVariable int userId) {
            groupsService.assignUsersToGroups(groupId, userId);
            return ResponseEntity.status(200).body("group assigned successfully");
     }
 // ---------------------------------------------------------- Nora end point -------------------------------------------
    @DeleteMapping("/{groupId}/members/{userId}") // remove member from group : nora
    public ResponseEntity<String> removeMemberFromGroup(@PathVariable Integer groupId, @PathVariable Integer userId) {
        groupsService.removeMember(groupId, userId);
        return ResponseEntity.status(200).body("Member removed successfully from the group.");
    }


    @PostMapping("/{groupId}/{type}")// nora
    public ResponseEntity<String> assignTaskToGroup(@PathVariable Integer groupId,@PathVariable String type, @Valid@RequestBody Tasks tasks) {
        groupsService.assignTaskToGroup(groupId,type ,tasks);
        return ResponseEntity.status(200).body("task assigned successfully");
    }


  @GetMapping("/getTask/{groupId}")
  public ResponseEntity getTask(@PathVariable Integer groupId) {
    return ResponseEntity.status(200).body(groupsService.groupTasks(groupId));
   }
          //            /api/v1/group/1/tichnical
          //            {
    //  "taskName": "Complete Assignment",
    //  "dueDate": "2023-10-15",
    //  "details": "Finish coding task for Spring Boot project"
    //}


    //  "name": "Development Team",
    //  "groupType": "Software Development",
    //  "description": "Group focused on building innovative software solutions",
    //  "teamPerformance": 8,
    //  "projects": ["Project A", "Project B"],
    //  "applicable": true,
    //  "requiredSkills": ["Java", "Spring Boot", "React"]


    @PostMapping("/{groupId}/tasks/{taskId}/performance/{ratingTeamPerform}")// nora
    public ResponseEntity assignTeamPerformanceReport(@PathVariable Integer groupId, @PathVariable Integer taskId, @PathVariable int ratingTeamPerform) {
            groupsService.assignTeamPerformanceReport(groupId, taskId, ratingTeamPerform);
            return ResponseEntity.status(200).body("team performance report assigned successfully");
    }



    // /api/v1/group/1/tasks/1/performance/3


 // ---------------------------------------------------- mohammad end point ---------------------------------------------
//
//    @PostMapping("/invite/{groupId}/{senderId}/{receiverId}")// mohammad
//    public ResponseEntity inviteUserToGroup(@PathVariable Integer groupId, @PathVariable Integer senderId, @PathVariable Integer receiverId) {
//        groupsService.inviteUserToGroup(groupId, senderId, receiverId);
//        return ResponseEntity.status(200).body("Invitation sent successfully");
//    }
//
//    @PutMapping("/accept/{invitationId}")// mohammad
//    public ResponseEntity acceptInvitation(@PathVariable Integer invitationId) {
//        groupsService.acceptInvitation(invitationId);
//        return ResponseEntity.status(200).body("Invitation accepted successfully");
//    }
//
//    @GetMapping("/members/{groupId}")// mohammad
//    public ResponseEntity<List<User>> getGroupMembers(@PathVariable Integer groupId) {
//        List<User> members = groupsService.getGroupMembers(groupId);
//        return ResponseEntity.status(200).body(members);
//    }
//
//
//    @PutMapping("/decline/{invitationId}")// mohammad
//    public ResponseEntity declineInvitation(@PathVariable Integer invitationId) {
//        groupsService.declineInvitation(invitationId);
//        return ResponseEntity.status(200).body("Invitation declined successfully");
//    }
//
//    @GetMapping("/pending/{userId}")// mohammad
//    public ResponseEntity<List<GroupInvitation>> getPendingInvitationsForUser(@PathVariable Integer userId) {
//        List<GroupInvitation> invitations = groupsService.getPendingInvitationsForUser(userId);
//        return ResponseEntity.status(200).body(invitations);
//    }
//
//    @GetMapping("/sent/{leaderId}")// mohammad
//    public ResponseEntity<List<GroupInvitation>> getSentInvitationsByLeader(@PathVariable Integer leaderId) {
//        List<GroupInvitation> invitations = groupsService.getSentInvitationsByLeader(leaderId);
//        return ResponseEntity.status(200).body(invitations);
//    }
//
//
//    @PostMapping("/{groupId}/transfer-leadership") // mohammad
//    public ResponseEntity<String> transferLeadership(@PathVariable Integer groupId, @RequestParam Integer newLeaderId) {
//        groupsService.transferLeadership(groupId, newLeaderId);
//        return ResponseEntity.status(200).body("Leadership transferred successfully");
//    }
//
//














//    @PutMapping("/assign/{groupId}/{userId}")
//    public ResponseEntity assignGroup(@PathVariable int groupId, @PathVariable int userId) {
//        groupsService.assignUsersToGroups(groupId, userId);
//        return ResponseEntity.status(200).body("group assigned successfully");
//    }



//    @PostMapping("/groups/{groupId}/decline-invite")// decline group invite
//    public ResponseEntity<String> declineGroupInvite(@PathVariable Integer groupId) {
//        groupsService.declineGroupInvite(groupId);
//        return ResponseEntity.ok("Invitation declined successfully");
//    }

}
