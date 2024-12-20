package ru.netology.dockerfile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dockerfile.exception.InvalidCredentials;
import ru.netology.dockerfile.exception.UnauthorizedUser;
import ru.netology.dockerfile.model.Authorities;
import ru.netology.dockerfile.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    private AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    private List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("PASSWORD") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    private ResponseEntity<String> handleInvalidCredentials (InvalidCredentials e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>( "EXCEPTION: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    private ResponseEntity<String> handleUnauthorizedUser (UnauthorizedUser e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>("EXCEPTION: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
