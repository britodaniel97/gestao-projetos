package com.britodaniel97.gestao_projetos.repository;

import com.britodaniel97.gestao_projetos.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
}
