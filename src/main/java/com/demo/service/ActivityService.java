//package com.demo.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.demo.model.Activity;
//import com.demo.repository.ActivityRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class ActivityService {
//
//    @Autowired
//    private ActivityRepository repository;
//
//    public Activity save(Activity activity) {
//        return repository.save(activity);
//    }
//
//    public List<Activity> getByDate(LocalDate date) {
//        return repository.findByDate(date);
//    }
//
//    public List<Activity> getByDateRange(LocalDate start, LocalDate end) {
//        return repository.findByDateRange(start, end);
//    }
//
//    public Activity update(Long id, Activity updated) {
//        Activity existing = repository.findById(id).orElseThrow();
//        existing.setTitle(updated.getTitle());
//        existing.setCategory(updated.getCategory());
//        existing.setDescription(updated.getDescription());
//        existing.setStartTime(updated.getStartTime());
//        existing.setEndTime(updated.getEndTime());
//        existing.setDate(updated.getDate());
//        return repository.save(existing);
//    }
//
//    public void delete(Long id) {
//        repository.deleteById(id);
//    }
//}
//
