package remo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import remo.backend.dto.MediaLikesDto;
import remo.backend.entity.Media;
import remo.backend.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/{mediaId}")
    @PreAuthorize("@groupSecurityService.canViewMedia(#mediaId, authentication.name)")
    public ResponseEntity<Media> getMediaById(@PathVariable Long mediaId) {
        return mediaService.getMediaById(mediaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{mediaId}/like")
    @PreAuthorize("@groupSecurityService.canViewMedia(#mediaId, authentication.name)")
    public ResponseEntity<Void> likeMedia(@PathVariable Long mediaId, Authentication authentication) {
        mediaService.likeMedia(mediaId, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{mediaId}/like")
    @PreAuthorize("@groupSecurityService.canViewMedia(#mediaId, authentication.name)")
    public ResponseEntity<Void> unlikeMedia(@PathVariable Long mediaId, Authentication authentication) {
        mediaService.unlikeMedia(mediaId, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{mediaId}/likes")
    @PreAuthorize("@groupSecurityService.canViewMedia(#mediaId, authentication.name)")
    public ResponseEntity<MediaLikesDto> getMediaLikes(@PathVariable Long mediaId, Authentication authentication) {
        MediaLikesDto result = mediaService.getMediaLikes(mediaId, authentication.getName());
        return ResponseEntity.ok(result);
    }
}
