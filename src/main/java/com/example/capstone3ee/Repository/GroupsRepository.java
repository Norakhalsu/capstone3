package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {
    Groups findGroupByGroupId(Integer id);

}
