package auca.ac.rw.question6_UserProfile.controller;

import auca.ac.rw.question6_UserProfile.modal.UserProfile;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private List<UserProfile> users = new ArrayList<>();
    private long id = 1;

    @PostMapping
    public Response createUser(@RequestBody UserProfile user) {
        user.setUserID(id++);
        user.setActive(true);
        users.add(user);
        return new Response(true, "User profile created successfully", user);
    }

    @GetMapping("/{id}")
    public Response getUser(@PathVariable long id) {
        for (UserProfile user : users) {
            if (user.getUserID() == id) return new Response(true, "User retrieved successfully", user);
        }
        return new Response(false, "User not found", null);
    }

    @GetMapping
    public Response getAllUsers() {
        return new Response(true, "Users retrieved successfully", users);
    }

    @PutMapping("/{id}")
    public Response updateUser(@PathVariable long id, @RequestBody UserProfile updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == id) {
                updatedUser.setUserID(id);
                users.set(i, updatedUser);
                return new Response(true, "User profile updated successfully", updatedUser);
            }
        }
        return new Response(false, "User not found", null);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == id) {
                users.remove(i);
                return new Response(true, "User profile deleted successfully", null);
            }
        }
        return new Response(false, "User not found", null);
    }

    @GetMapping("/search/username")
    public Response searchByUsername(@RequestParam String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getUsername().toLowerCase().contains(username.toLowerCase())) result.add(user);
        }
        return new Response(true, "Search completed", result);
    }

    @GetMapping("/search/country")
    public Response searchByCountry(@RequestParam String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) result.add(user);
        }
        return new Response(true, "Search completed", result);
    }

    @GetMapping("/search/age")
    public Response searchByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) result.add(user);
        }
        return new Response(true, "Search completed", result);
    }

    @PatchMapping("/{id}/activate")
    public Response activateUser(@PathVariable long id) {
        for (UserProfile user : users) {
            if (user.getUserID() == id) {
                user.setActive(true);
                return new Response(true, "User profile activated successfully", user);
            }
        }
        return new Response(false, "User not found", null);
    }

    @PatchMapping("/{id}/deactivate")
    public Response deactivateUser(@PathVariable long id) {
        for (UserProfile user : users) {
            if (user.getUserID() == id) {
                user.setActive(false);
                return new Response(true, "User profile deactivated successfully", user);
            }
        }
        return new Response(false, "User not found", null);
    }

    static class Response {
        public boolean success;
        public String message;
        public Object data;

        public Response(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }
    }
}
