package com.example.demo.service;

import com.example.demo.model.Workout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

@SpringBootTest
public class WorkoutTests {

    @Autowired
    private WorkoutService workoutService;

    @Test
    @WithMockUser(username = "gculum")
    public void testWorkoutFetch() {
        List<Workout> workouts = workoutService.findWorkouts();
        Assertions.assertEquals(2, workouts.size());
    }
}

