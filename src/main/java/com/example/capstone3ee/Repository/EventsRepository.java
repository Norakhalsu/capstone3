package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {
    Events findEventsById(int id);
}
