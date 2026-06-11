package remo.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PendingRegistration {
    private String firstname;
    private String lastname;
    private String email;
    private String passwordHash;
    private UUID token;
    private Instant createdAt;
}

