package remo.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import remo.backend.entity.Account;
import remo.backend.entity.UserProfile;
import remo.backend.repository.AccountRepository;

@Component("userProfileSecurity")
public class UserProfileSecurity {
    private final AccountRepository accountRepository;
    @Autowired
    public UserProfileSecurity(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public boolean isOwner(Authentication authentication, Long profileId) {
        if (authentication == null || profileId == null) {
            return false;
        }
        return accountRepository.findAccountByUsername(authentication.getName())
                .map(Account::getUserProfile)
                .map(UserProfile::getId)
                .map(profileId::equals)
                .orElse(false);
    }
}
