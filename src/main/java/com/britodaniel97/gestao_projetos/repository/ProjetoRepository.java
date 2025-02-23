package com.britodaniel97.gestao_projetos.repository;

import com.britodaniel97.gestao_projetos.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
}
