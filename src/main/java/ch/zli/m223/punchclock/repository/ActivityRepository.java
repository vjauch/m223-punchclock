package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
