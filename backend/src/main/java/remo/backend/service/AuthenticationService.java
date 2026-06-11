package remo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import remo.backend.dto.AuthenticationDto;
import remo.backend.dto.TokenDto;
import remo.backend.repository.AccountRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
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
}
