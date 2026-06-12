package remo.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import remo.backend.security.Role;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String email;
    private String loginToken;
    @Enumerated(EnumType.STRING)
    private Role role;
}
