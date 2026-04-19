package com.ultrafit.ultrafit.controller;

import com.ultrafit.ultrafit.model.Trainer;
import com.ultrafit.ultrafit.service.TrainerService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainers")
public class TrainerRestController {
    //Here we have the .java to control all the trainers
    private final TrainerService trainerService;

    public TrainerRestController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
    // its the same thing that the member, to get all the trainers we just return the list of trainers
    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }
    //To find a trainer by is id in the HashMap. Returns null if not exists
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Trainer trainer = trainerService.getTrainerById(id);
        return (trainer == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(trainer);
    }
    //we save the trainer in HashMap
    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        Trainer created = trainerService.createTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    //funtion to update the trainer
    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        Trainer updated = trainerService.updateTrainer(id, trainer);
        return ResponseEntity.ok(updated);
    }
    //same thing that appears in the MemberService, partial update of the trainer
    @PatchMapping("/{id}")
    public ResponseEntity<Trainer> patchTrainer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Trainer patched = trainerService.patchTrainer(id, updates);
        return ResponseEntity.ok(patched);
    }
    //to delete the trainer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
