package com.francodavyd.repository;

import com.francodavyd.model.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarrioRepository extends JpaRepository<Barrio, Long> {
}
