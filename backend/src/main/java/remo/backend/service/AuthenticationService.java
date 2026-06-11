package remo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import remo.backend.dto.AuthenticationDto;
import remo.backend.dto.RegisterDto;
import remo.backend.dto.TokenDto;
import remo.backend.entity.Account;
import remo.backend.entity.PendingRegistration;
import remo.backend.repository.AccountRepository;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;
    private final MailService mailService;
    private final AccountService accountService;

    @Autowired
    public AuthenticationService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, RegistrationService registrationService, MailService mailService, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
        this.mailService = mailService;
        this.accountService = accountService;
    }

    public Optional<TokenDto> authenticateUser(AuthenticationDto authDto) {
        return accountRepository.findAccountByUsername(authDto.username())
                .filter(account -> passwordEncoder.matches(
                        authDto.passwordHash(),
                        account.getPasswordHash()))
                .map(account -> {
                    String token = UUID.randomUUID().toString();
                    account.setLoginToken(token);
                    accountRepository.save(account);
                    return new TokenDto(token);
                });
    }

    public boolean authorizeUser(String token) {
        return accountRepository.findAccountByLoginToken(token).isPresent();
    }

    public HttpStatus registrateUser(RegisterDto registerDto) {
        if (accountRepository.findAccountByEmail(registerDto.email()).isEmpty() && !registrationService.mailExists(registerDto.email())) {
            String passwordHash = passwordEncoder.encode(registerDto.password());
            UUID registrationToken = registrationService.startRegistration(registerDto, passwordHash);
            mailService.sendRegistrationVerification(registerDto.email(), registrationToken);
        } else {
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.CREATED;
    }

    public HttpStatus confirmRegistration(UUID token) {
        Optional<PendingRegistration> pendingRegistrationOptional = registrationService.getPendingRegistration(token);
        if (pendingRegistrationOptional.isPresent()) {
            PendingRegistration pendingRegistration = pendingRegistrationOptional.get();
            if (pendingRegistration.getCreatedAt().isAfter(Instant.now().minus(Duration.ofDays(1)))) {
                accountService.createAccount(Account.builder()
                                .firstName(pendingRegistration.getFirstname())
                                .lastName(pendingRegistration.getLastname())
                                .username(pendingRegistration.getEmail())
                                .email(pendingRegistration.getEmail())
                                .passwordHash(pendingRegistration.getPasswordHash())
                        .build());
                registrationService.removePendingRegistration(token);
                return HttpStatus.OK;
            } else {
                return HttpStatus.GONE;
            }
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
