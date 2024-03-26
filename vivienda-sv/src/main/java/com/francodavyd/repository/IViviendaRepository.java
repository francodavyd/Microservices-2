package com.francodavyd.repository;

import com.francodavyd.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IViviendaRepository extends JpaRepository<Vivienda, Long> {
}
