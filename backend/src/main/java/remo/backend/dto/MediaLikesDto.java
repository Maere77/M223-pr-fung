package remo.backend.dto;

import java.util.List;

public record MediaLikesDto(
        Long mediaId,
        int likesCount,
        List<String> likedBy
) {}
