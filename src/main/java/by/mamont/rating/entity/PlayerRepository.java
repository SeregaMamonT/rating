package by.mamont.rating.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>
{
  @Query("Select p from Player p where p.surname like :surname% and p.name like :name% and p.patronymic like :patronymic%")
  Page<Player> findByLettersContaining(
    @Param("surname") String surname,
    @Param("name") String name,
    @Param("patronymic") String patronymic,
    Pageable pageable);
}
