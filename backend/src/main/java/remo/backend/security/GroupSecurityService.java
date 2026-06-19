package remo.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import remo.backend.entity.Group;
import remo.backend.entity.Media;
import remo.backend.repository.GroupRepository;
import remo.backend.repository.MediaRepository;

@Service
@RequiredArgsConstructor
public class GroupSecurityService {

    private final GroupRepository groupRepository;
    private final MediaRepository mediaRepository;

    public boolean isMediaOwner(Long mediaId, String username) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(NullPointerException::new);
        if (media.getGroup() == null) return false;
        return isOwner(media.getGroup().getId(), username);
    }

    public boolean canViewMedia(Long mediaId, String username) {
        Media media = mediaRepository.findById(mediaId)
                .orElse(null);
        if (media == null)
            return false;
        Group group = media.getGroup();
        if (group == null)
            return false;
        return isMember(group.getId(), username);
    }

    public boolean isMember(Long groupId, String username) {

        Group group = groupRepository.findById(groupId)
                .orElse(null);

        if (group == null)
            return false;

        return group.getMembers().stream()
                .anyMatch(member ->
                        member.getUsername().equals(username));
    }


    public boolean isOwner(Long groupId, String username) {

        Group group = groupRepository.findById(groupId)
                .orElse(null);

        return group != null
                && group.getOwner().getUsername().equals(username);
    }
}
