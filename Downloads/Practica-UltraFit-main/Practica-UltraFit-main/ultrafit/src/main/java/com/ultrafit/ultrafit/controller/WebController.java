package com.ultrafit.ultrafit.controller;

import com.ultrafit.ultrafit.model.Reservation;
import com.ultrafit.ultrafit.model.Trainer;
import com.ultrafit.ultrafit.model.Member;
import com.ultrafit.ultrafit.service.ReservationService;
import com.ultrafit.ultrafit.service.MemberService;
import com.ultrafit.ultrafit.service.TrainerService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final TrainerService trainerService;

    public WebController(ReservationService reservationService, MemberService memberService, TrainerService trainerService) {
        this.reservationService = reservationService;
        this.memberService = memberService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
        // Hardcoded credentials for Part I — no database yet.
        // In Part II this will be replaced with a real user lookup.
        if ("user".equals(username) && "1234".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        }
        return "redirect:/login?error=true";
    }

    @GetMapping("/register")
    public String register(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session) {
        // In Part I, registration only creates a session.
        // Persistent storage will be added in Part II with a database.
        session.setAttribute("user", username);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("user");

        if (username == null) {
            return "redirect:/login";
        }

        // get plan from session, default to Basic Plan if not set
        String plan = (String) session.getAttribute("plan");
        if (plan == null) {
             plan = "Basic Plan";
        }

        Member currentUser = new Member();
        currentUser.setName(username);
        currentUser.setPlan(plan);

        model.addAttribute("trainers", trainerService.getAllTrainers());
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("userMember", currentUser);
        model.addAttribute("reservations", reservationService.getReservationsByUsername(username));

        return "dashboard";
    }

    @PostMapping("/reservations/create")
    public String createReservation(@RequestParam Long memberId,
                                    @RequestParam Long trainerId,
                                    @RequestParam String date,
                                    @RequestParam String time,
                                    @RequestParam String level,
                                    HttpSession session) {
        Reservation r = new Reservation();
        r.setMemberId(memberId);
        r.setTrainerId(trainerId);
        r.setDate(date);
        r.setTime(time);
        r.setLevel(level);
        r.setUsername((String) session.getAttribute("user"));

        reservationService.createReservation(r);
        return "redirect:/dashboard";
    }

    @PostMapping("/reservations/delete")
    public String deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/reservations/edit")
    public String editReservation(@RequestParam Long id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        model.addAttribute("reservation", reservationService.getReservationById(id));
        return "editReservation";
    }

    @PostMapping("/reservations/update")
    public String updateReservation(@RequestParam Long id,
                                @RequestParam Long memberId,
                                @RequestParam Long trainerId,
                                @RequestParam String date,
                                @RequestParam String time,
                                @RequestParam String level,
                                HttpSession session) {

    Reservation r = new Reservation();
    r.setId(id);
    r.setMemberId(memberId);
    r.setTrainerId(trainerId);
    r.setDate(date);
    r.setTime(time);
    r.setLevel(level);
    r.setUsername((String) session.getAttribute("user"));

            reservationService.updateReservation(id, r);
            return "redirect:/dashboard";
    }

    @PostMapping("/plan/update")
    public String updatePlan(@RequestParam String plan, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
            session.setAttribute("plan", plan); // save plan in session
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
            HttpSession session,
            Model model) {

        System.out.println("Mensaje recibido:");
        System.out.println(nombre + " " + apellido);
        System.out.println(email);
        System.out.println(telefono);
        System.out.println(centro);
        System.out.println(mensaje);

        if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        model.addAttribute("success", true);
        return "index";
    }
    @GetMapping("/faq")
    public String faq(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        }
        return "faq";
    }
}