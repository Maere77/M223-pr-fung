package remo.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {
    public void sendRegistrationVerification(String email, UUID token) {
        String link = "http://localhost:8080/auth/register/confirm/" + token;
        System.out.println("Regestrierungslinke für " + email + ": " + link);
    }
}
