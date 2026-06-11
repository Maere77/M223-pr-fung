package remo.backend.service;

import org.springframework.stereotype.Service;
import remo.backend.dto.RegisterDto;
import remo.backend.entity.PendingRegistration;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {

    private final HashMap<UUID, PendingRegistration> pendingRegistrations = new HashMap<>();

    public UUID startRegistration(RegisterDto registerDto, String passwordHash) {
        UUID registrationToken = UUID.randomUUID();
        pendingRegistrations.put(registrationToken, new PendingRegistration(
                registerDto.firstName(),
                registerDto.lastName(),
                registerDto.email(),
                passwordHash,
                registrationToken,
                Instant.now()
        ));
        return registrationToken;
    }

    public Optional<PendingRegistration> getPendingRegistration(UUID token) {
        return Optional.ofNullable(pendingRegistrations.get(token));
    }

    public void removePendingRegistration(UUID token) {
        pendingRegistrations.remove(token);
    }

    public boolean mailExists(String email) {
        return pendingRegistrations.values().stream()
                .anyMatch(pendingRegistration -> pendingRegistration.getEmail().equalsIgnoreCase(email));
    }
}
