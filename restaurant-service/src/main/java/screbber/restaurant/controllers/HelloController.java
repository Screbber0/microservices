package screbber.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import screbber.restaurant.repositories.PeopleRepository;
import screbber.restaurant.services.JWTUtil;


@RestController
public class HelloController {

    private final JWTUtil jwtUtil;

    private final PeopleRepository peopleRepository;

    @Autowired
    public HelloController(JWTUtil jwtUtil, PeopleRepository peopleRepository) {
        this.jwtUtil = jwtUtil;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/hello")
    public String test() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
            int person_id = peopleRepository.findByUsername(username).orElseThrow().getId();
            return username;

        } else {
            return "No Authorization header found";
        }
    }
}

