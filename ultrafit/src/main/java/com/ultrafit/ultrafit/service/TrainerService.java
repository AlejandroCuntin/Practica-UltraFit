package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Trainer;

import jakarta.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import java.util.*;

@Service
public class TrainerService {
    //we create the HashMap to save the trainers in it
    private final Map<Long, Trainer> trainers = new HashMap<>();
    private Long nextId = 1L;
    //Load data from data.josn
    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var root = mapper.readTree(new ClassPathResource("/data/data.json").getInputStream());
            for (var node : root.get("trainers")) {
                Trainer t = new Trainer(
                    node.get("id").asLong(),
                    node.get("name").asText(),
                    node.get("specialty").asText(),
                    node.get("experienceYears").asInt()
                );
                trainers.put(t.getId(), t);
                if (t.getId() >= nextId) nextId = t.getId() + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
