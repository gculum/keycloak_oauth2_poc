package com.example.demo.service;

import com.example.demo.errorhandling.ResourceNotFoundException;
import com.example.demo.model.Workout;
import com.example.demo.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @PreAuthorize("#workout.user == authentication.name and hasAuthority('SCOPE_fitnessapp')")
    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public List<Workout> findWorkouts() {
        return workoutRepository.findAllByUser();
    }

    public boolean deleteWorkout(Integer id) {
        if(workoutRepository.findById(id).isPresent()) {
            workoutRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException(id);
    }
}