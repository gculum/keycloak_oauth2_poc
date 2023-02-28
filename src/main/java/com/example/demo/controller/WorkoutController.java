package com.example.demo.controller;

import com.example.demo.model.Workout;
import com.example.demo.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping()
    public ResponseEntity<Workout> add(@RequestBody Workout workout) {
        Workout workoutSave = workoutService.saveWorkout(workout);
        return new ResponseEntity<>(workoutSave, workout != null ?
                HttpStatus.CREATED : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping()
    public List<Workout> findAll() {

        return workoutService.findWorkouts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Workout> delete(@PathVariable Integer id) {
        workoutService.deleteWorkout(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}