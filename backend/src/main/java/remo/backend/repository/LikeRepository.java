package remo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import remo.backend.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}

