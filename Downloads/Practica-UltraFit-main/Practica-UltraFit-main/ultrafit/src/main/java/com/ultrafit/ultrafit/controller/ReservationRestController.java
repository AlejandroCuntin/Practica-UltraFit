package com.ultrafit.ultrafit.controller;

import com.ultrafit.ultrafit.model.Reservation;
import com.ultrafit.ultrafit.service.ReservationService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation r = reservationService.getReservationById(id);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updated = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> patchReservation(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Reservation patched = reservationService.patchReservation(id, updates);
        return ResponseEntity.ok(patched);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
