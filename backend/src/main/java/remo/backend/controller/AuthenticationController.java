package remo.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import remo.backend.dto.AuthenticationDto;
import remo.backend.dto.RegisterDto;
import remo.backend.dto.TokenDto;
import remo.backend.service.AuthenticationService;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/register/confirm/{token}")
    public ResponseEntity<?> confirm(@PathVariable UUID token) {
        return authenticationService.confirmRegistration(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto) {
        return authenticationService.registrateUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authDto) {
        return authenticationService.authenticateUser(authDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedResource(@RequestHeader("Authorization") String authHeader) {
        return authenticationService.authorizeUser(authHeader)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
