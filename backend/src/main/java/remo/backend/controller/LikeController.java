package remo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import remo.backend.service.LikeService;

import java.util.Map;

@RestController
@RequestMapping("/api/me/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getLikes(Authentication authentication) {
        int count = likeService.getLikeCount(authentication.getName());
        return ResponseEntity.ok(Map.of("likesCount", count));
    }

    @PostMapping("/like")
    public ResponseEntity<Map<String, Integer>> like(@RequestParam Long userId) {
        int updatedCount = likeService.likeUser(userId);
        return ResponseEntity.ok(Map.of("likesCount", updatedCount));
    }

    @PostMapping("/dislike")
    public ResponseEntity<Map<String, Integer>> dislike(@RequestParam Long userId) {
        int updatedCount = likeService.dislikeUser(userId);
        return ResponseEntity.ok(Map.of("likesCount", updatedCount));
    }
}
