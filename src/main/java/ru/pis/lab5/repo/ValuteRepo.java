package ru.pis.lab5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pis.lab5.entity.ValuteEntity;

import java.util.List;

@Repository
public interface ValuteRepo extends JpaRepository<ValuteEntity, Long> {
    @Query("SELECT vals FROM ValuteEntity vals")
    List<ValuteEntity> getAll();

    ValuteEntity getById(Long id);
}
