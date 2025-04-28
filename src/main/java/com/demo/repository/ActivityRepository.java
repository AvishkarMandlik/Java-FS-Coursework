package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.model.Activity;
import com.demo.model.User;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDate(LocalDate date);
    
//    List<Activity> findByCategory(String category);

    List<Activity> findByDateAndUser(LocalDate date, User user);
    
    @Query("SELECT a FROM Activity a WHERE a.date BETWEEN :start AND :end")
    List<Activity> findByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    @Query("SELECT a FROM Activity a WHERE a.date BETWEEN :start AND :end AND a.user = :user")
    List<Activity> findByDateRangeAndUser(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("user") User user);
}