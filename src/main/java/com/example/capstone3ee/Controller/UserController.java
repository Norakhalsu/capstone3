package com.example.capstone3ee.Controller;

import com.example.capstone3ee.Model.User;
import com.example.capstone3ee.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
       userService.addUser(user);
        return ResponseEntity.status(200).body("user added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
    userService.updateUser(id, user);
        return ResponseEntity.status(200).body("user updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("user deleted");
    }
     // --------------------------------------- end point ------------------------------------

    @PostMapping("/{userId}/requests/{expertId}")// nora
    public ResponseEntity<String> createRequest(@PathVariable Integer userId, @PathVariable Integer expertId, @RequestBody String requestDescription) {
        userService.createRequest(userId, expertId, requestDescription);
        return ResponseEntity.ok("Request created successfully");
    }

    @GetMapping("/compare/{userId1}/{userId2}")// yara
    public ResponseEntity compareUsers(@PathVariable Integer userId1, @PathVariable Integer userId2) {
        return ResponseEntity.status(200).body(userService.compareUsers(userId1, userId2));
    }

    //YARA ENDPOINT
    private List<String> whatsNews = new ArrayList<>();

    // POST /whats/new  YARA  - Add a list of two updates or announcements for users
    @PostMapping("/new")
    public ResponseEntity addNewUserUpdates(@RequestBody List<String> updates) {
        whatsNews.clear(); // Clear existing updates
        whatsNews.addAll(updates);
        return ResponseEntity.ok("New updates added successfully!");
    }

    // GET /WHATS/new - Retrieve the list of updates or announcements for users
    @GetMapping("/new")
    public ResponseEntity getUserNews() {
        return ResponseEntity.ok(whatsNews);
    }





}