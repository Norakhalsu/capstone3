package com.example.capstone3ee.Repository;

//package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.GroupInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupInvitationRepository extends JpaRepository<GroupInvitation, Integer> {
    //   List<GroupInvitation> findByReceiverIdAndStatus(Integer receiverId, String status);
    // List<GroupInvitation> findBySenderId(Integer senderId);

}