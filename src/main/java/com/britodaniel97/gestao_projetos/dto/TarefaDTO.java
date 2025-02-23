package com.britodaniel97.gestao_projetos.dto;

import com.britodaniel97.gestao_projetos.enums.TaskStatus;
import java.util.UUID;

public record TarefaDTO(
        UUID id,
        String title,
        String description,
        String assignedToName,
        String projetoName,
        UUID projetoId,
        TaskStatus status
) {}
