package com.rh.repositories;

import com.rh.entities.FuncionarioLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioLogRepository extends JpaRepository<FuncionarioLog, Long> {
}