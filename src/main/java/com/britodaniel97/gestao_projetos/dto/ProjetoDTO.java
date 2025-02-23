package com.britodaniel97.gestao_projetos.dto;

import com.britodaniel97.gestao_projetos.entity.Usuario;
import com.britodaniel97.gestao_projetos.enums.ProjectStatus;

import java.util.List;
import java.util.UUID;

public record ProjetoDTO(
        UUID id,
        String name,
        String description,
        Usuario manager,
        ProjectStatus status,
        List<TarefaDTO> tarefas
) {}
