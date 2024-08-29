package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Expert;
import com.example.capstone3ee.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsersId(Integer id);
   //User findUserBy(Expert expert);
}
