package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final Map<Long, Reservation> reservations = new HashMap<>();
    private Long nextId = 1L;

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    public Reservation getReservationById(Long id) {
        return reservations.get(id);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        updatedReservation.setId(id);
        reservations.put(id, updatedReservation);
        return updatedReservation;
    }

    public Reservation patchReservation(Long id, Map<String, Object> updates) {
        Reservation reservation = reservations.get(id);
        if (reservation == null) return null;

        if (updates.containsKey("date")) {
            reservation.setDate((String) updates.get("date"));
        }
        if (updates.containsKey("time")) {
            reservation.setTime((String) updates.get("time"));
        }
        if (updates.containsKey("level")) {
            reservation.setLevel((String) updates.get("level"));
        }

        return reservation;
    }

    public void deleteReservation(Long id) {
        reservations.remove(id);
    }

    public List<Reservation> getReservationsByUsername(String username) {
    return reservations.values().stream()
            .filter(r -> username.equals(r.getUsername()))
            .collect(java.util.stream.Collectors.toList());
    }
}
