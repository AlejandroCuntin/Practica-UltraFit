package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainerService {

    private final Map<Long, Trainer> trainers = new HashMap<>();
    private Long nextId = 1L;

    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainers.values());
    }

    public Trainer getTrainerById(Long id) {
        return trainers.get(id);
    }

    public Trainer createTrainer(Trainer trainer) {
        trainer.setId(nextId++);
        trainers.put(trainer.getId(), trainer);
        return trainer;
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        updatedTrainer.setId(id);
        trainers.put(id, updatedTrainer);
        return updatedTrainer;
    }

    public Trainer patchTrainer(Long id, Map<String, Object> updates) {
        Trainer trainer = trainers.get(id);
        if (trainer == null) return null;

        if (updates.containsKey("name")) {
            trainer.setName((String) updates.get("name"));
        }
        if (updates.containsKey("specialty")) {
            trainer.setSpecialty((String) updates.get("specialty"));
        }
        if (updates.containsKey("experienceYears")) {
            trainer.setExperienceYears((int) updates.get("experienceYears"));
        }

        return trainer;
    }

    public void deleteTrainer(Long id) {
        trainers.remove(id);
    }
}
