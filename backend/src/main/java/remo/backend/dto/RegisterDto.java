package remo.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public record RegisterDto(@NonNull
                          @NotEmpty
                          String firstName,
                          @NonNull
                          @NotEmpty
                          String lastName,
                          @Email
                          String email,
                          @NonNull
                          @NotEmpty
                          String password) {
}
