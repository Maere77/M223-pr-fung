package remo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remo.backend.entity.Media;
import remo.backend.repository.MediaRepository;

import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public Optional<Media> getMediaById(Long mediaId) {
        return mediaRepository.findById(mediaId);
    }
}
