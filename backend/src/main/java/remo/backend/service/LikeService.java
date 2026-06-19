package remo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remo.backend.entity.Account;
import remo.backend.entity.Like;
import remo.backend.repository.AccountRepository;
import remo.backend.repository.LikeRepository;

@Service
public class LikeService {

    private final AccountRepository accountRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(AccountRepository accountRepository, LikeRepository likeRepository) {
        this.accountRepository = accountRepository;
        this.likeRepository = likeRepository;
    }

    public int getLikeCount(String username) {
        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return account.getLike().getCountLikes();
    }

    @Transactional
    public int likeUser(Long targetUserId) {
        Account targetAccount = accountRepository.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("Target user not found: " + targetUserId));

        Like like = targetAccount.getLike();
        like.setCountLikes(like.getCountLikes() + 1);
        likeRepository.save(like);

        return like.getCountLikes();
    }

    @Transactional
    public int dislikeUser(Long targetUserId) {
        Account targetAccount = accountRepository.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("Target user not found: " + targetUserId));

        Like like = targetAccount.getLike();
        int newCount = Math.max(0, like.getCountLikes() - 1);
        like.setCountLikes(newCount);
        likeRepository.save(like);

        return like.getCountLikes();
    }
}

