package screbber.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import screbber.restaurant.repositories.PeopleRepository;
import screbber.restaurant.security.JWTUtil;

@RestController
public class HelloController {

    private final PeopleRepository peopleRepository;
    private final JWTUtil jwtUtil;

    @Autowired
    public HelloController(PeopleRepository peopleRepository, JWTUtil jwtUtil) {
        this.peopleRepository = peopleRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
            return username;

        } else {
            return "No Authorization header found";
        }
    }
}

