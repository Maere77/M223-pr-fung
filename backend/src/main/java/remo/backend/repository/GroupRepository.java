package remo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import remo.backend.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
