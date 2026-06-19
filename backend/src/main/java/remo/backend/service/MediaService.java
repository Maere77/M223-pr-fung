package remo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remo.backend.dto.MediaLikesDto;
import remo.backend.entity.Account;
import remo.backend.entity.Media;
import remo.backend.repository.AccountRepository;
import remo.backend.repository.MediaRepository;
import remo.backend.security.GroupSecurityService;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final AccountRepository accountRepository;
    private final GroupSecurityService groupSecurityService;

    @Autowired
    public MediaService(MediaRepository mediaRepository, AccountRepository accountRepository, GroupSecurityService groupSecurityService) {
        this.mediaRepository = mediaRepository;
        this.accountRepository = accountRepository;
        this.groupSecurityService = groupSecurityService;
    }

    public Optional<Media> getMediaById(Long mediaId) {
        return mediaRepository.findById(mediaId);
    }

    public void likeMedia(Long mediaId, String username) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));
        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean alreadyLiked = media.getLikedBy().stream()
                .anyMatch(a -> a.getId().equals(account.getId()));
        if (alreadyLiked) {
            throw new IllegalStateException("User already liked");
        }
        media.getLikedBy().add(account);
        mediaRepository.save(media);
    }

    public void unlikeMedia(Long mediaId, String username) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));
        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean wasLiked = media.getLikedBy().removeIf(a -> a.getId().equals(account.getId()));
        if (!wasLiked) {
            throw new IllegalStateException("User not liked");
        }
        mediaRepository.save(media);
    }

    //logik von Code completion
    public MediaLikesDto getMediaLikes(Long mediaId, String requestingUsername) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        int count = media.getLikedBy().size();
        boolean isOwner = groupSecurityService.isMediaOwner(mediaId, requestingUsername);

        List<String> likedBy = isOwner
                ? media.getLikedBy().stream().map(Account::getUsername).toList()
                : null;

        return new MediaLikesDto(mediaId, count, likedBy);
    }
//autocompletion für überprüfungs logik
    public void publishMedia(Long mediaId, String username) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new RuntimeException("Media not found"));
        if (!media.isSketch()) {
            throw new IllegalStateException("Media is not published");
        }
        if (!groupSecurityService.isMediaOwner(mediaId, username)) {
            throw new IllegalStateException("Unathorised");
        }
        media.setSketch(false);
        media.setPublished(true);
        mediaRepository.save(media);
    }
}
