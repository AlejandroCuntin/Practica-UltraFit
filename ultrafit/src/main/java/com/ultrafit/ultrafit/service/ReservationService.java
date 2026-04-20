package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    //we create a HashMap of reservations were the id of the reservation is the key to find it
    private final Map<Long, Reservation> reservations = new HashMap<>();
    private Long nextId = 1L; 
    //to get all reservations
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }
    //to get one reservation in particular, using the id
    public Reservation getReservationById(Long id) {
        return reservations.get(id);
    }
    //to create a new reservation
    public Reservation createReservation(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }
    //to update it
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        updatedReservation.setId(id);
        reservations.put(id, updatedReservation);
        return updatedReservation;
    }
    //and to create a partial update of the reservation. If we want to change something we are gonna use this function
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
    //and to get the reservations from a certain member, using is name
    public List<Reservation> getReservationsByUsername(String username) {
    return reservations.values().stream()
            .filter(r -> username.equals(r.getUsername()))
            .collect(java.util.stream.Collectors.toList());
    }
}
