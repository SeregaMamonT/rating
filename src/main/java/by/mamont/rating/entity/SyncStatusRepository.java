package by.mamont.rating.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncStatusRepository extends JpaRepository<SyncStatus, Integer>
{
}
