package remo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import remo.backend.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
