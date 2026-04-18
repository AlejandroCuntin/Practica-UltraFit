package com.ultrafit.ultrafit.controller;

import com.ultrafit.ultrafit.model.Reservation;
import com.ultrafit.ultrafit.service.ReservationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final ReservationService reservationService;

    public WebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {

        if ("user".equals(username) && "1234".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("reservations", reservationService.getAllReservations());
        return "dashboard";
    }

    @PostMapping("/reservations/create")
    public String createReservation(@RequestParam Long memberId,
                                    @RequestParam Long trainerId,
                                    @RequestParam String date,
                                    @RequestParam String time,
                                    @RequestParam String level) {

        Reservation r = new Reservation();
        r.setMemberId(memberId);
        r.setTrainerId(trainerId);
        r.setDate(date);
        r.setTime(time);
        r.setLevel(level);

        reservationService.createReservation(r);
        return "redirect:/dashboard";
    }

    @PostMapping("/reservations/delete")
    public String deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/reservations/edit")
    public String editReservation(@RequestParam Long id, Model model) {
        model.addAttribute("reservation", reservationService.getReservationById(id));
        return "editReservation";
    }

    @PostMapping("/reservations/update")
    public String updateReservation(@RequestParam Long id,
                                    @RequestParam Long memberId,
                                    @RequestParam Long trainerId,
                                    @RequestParam String date,
                                    @RequestParam String time,
                                    @RequestParam String level) {

        Reservation r = new Reservation();
        r.setId(id);
        r.setMemberId(memberId);
        r.setTrainerId(trainerId);
        r.setDate(date);
        r.setTime(time);
        r.setLevel(level);

        reservationService.updateReservation(id, r);
        return "redirect:/dashboard";
    }
    @PostMapping("/contact")
    public String contactForm(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String centro,
            @RequestParam String mensaje,
            Model model) {

        System.out.println("Mensaje recibido:");
        System.out.println(nombre + " " + apellido);
        System.out.println(email);
        System.out.println(telefono);
        System.out.println(centro);
        System.out.println(mensaje);

        model.addAttribute("success", true);
        return "index";
    }

}
