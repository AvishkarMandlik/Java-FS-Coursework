package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.model.Activity;
import com.demo.model.User;
import com.demo.repository.ActivityRepository;
import com.demo.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Activity create(@RequestBody Activity activity, @RequestHeader String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        activity.setUser(user);
        return repository.save(activity);
    }

    @GetMapping
    public List<Activity> getByDate(@RequestParam String date, @RequestHeader String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        
        if ("ADMIN".equals(user.getRole())) {
            return repository.findByDate(LocalDate.parse(date));
        }
        return repository.findByDateAndUser(LocalDate.parse(date), user);
    }
    
    
    @GetMapping("/between")
    public List<Activity> getActivitiesBetweenDates(@RequestParam("start") String startDate,
                                                     @RequestParam("end") String endDate,
                                                     @RequestHeader String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if ("ADMIN".equals(user.getRole())) {
            return repository.findByDateRange(start, end);
        }
        return repository.findByDateRangeAndUser(start, end, user);
    }


    @PutMapping("/{id}")
    public Activity update(@PathVariable Long id, @RequestBody Activity updated, @RequestHeader String username) {
        User user = userRepository.findByUsername(username);
        Activity existing = repository.findById(id).orElseThrow();
        
        if (!existing.getUser().getUsername().equals(username) && !"ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Unauthorized access");
        }
        
        existing.setTitle(updated.getTitle());
        existing.setCategory(updated.getCategory());
        existing.setDescription(updated.getDescription());
        existing.setStartTime(updated.getStartTime());
        existing.setEndTime(updated.getEndTime());
        existing.setDate(updated.getDate());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestHeader String username) {
        User user = userRepository.findByUsername(username);
        Activity activity = repository.findById(id).orElseThrow();
        
        if (!activity.getUser().getUsername().equals(username) && !"ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Unauthorized access");
        }
        repository.deleteById(id);
    }

}