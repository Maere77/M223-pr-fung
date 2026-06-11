package remo.backend.dto;

public record AuthenticationDto(String username, String passwordHash) {
}
