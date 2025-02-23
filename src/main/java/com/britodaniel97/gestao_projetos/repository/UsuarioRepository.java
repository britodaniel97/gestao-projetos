package com.britodaniel97.gestao_projetos.repository;

import com.britodaniel97.gestao_projetos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
