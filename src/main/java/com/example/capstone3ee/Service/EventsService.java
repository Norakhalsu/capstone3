package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.Model.Events;
import com.example.capstone3ee.Model.Groups;
import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Repository.EventsRepository;
import com.example.capstone3ee.Repository.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final GroupsRepository groupsRepository;

    public List<Events> getAllEvents() {
       return eventsRepository.findAll();
    }

    public void addEvents(Events events) {
        eventsRepository.save(events);
    }

    public void deleteEvents(Integer id) {
        Events events = eventsRepository.findEventsById(id);
        if (events == null) {
            throw new ApiException("No events with id " + id + " found");
        }
        eventsRepository.delete(events);
    }


    public void updateEvents(Integer id,Events event) {
        Events event1 = eventsRepository.findEventsById(id);
        if (event1 == null) {
            throw new ApiException("No events with id " + id + " found");
        }
        event1.setOrganizer(event.getOrganizer());
        event1.setEventsRating(event.getEventsRating());
        event1.setName(event.getName());
        event1.setDescription(event.getDescription());
        event1.setStartDate(event.getStartDate());
        event1.setEndDate(event.getEndDate());
        eventsRepository.save(event1);
    }



    public void assignEventToGroup(Integer groupId, Integer eventId) {
        Groups group = groupsRepository.findGroupByGroupId(groupId);
        Events event = eventsRepository.findEventsById(eventId);

        if (group == null) {
            throw new ApiException("No group with id " + groupId + " found");
        }

        if (event == null) {
            throw new ApiException("No event with id " + eventId + " found");
        }

        group.getEvents().add(event);
        groupsRepository.save(group);
    }

    // --------------------------------- end points -------------------------------------

    // calculate duration of event  : by Nora
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





    }




}
