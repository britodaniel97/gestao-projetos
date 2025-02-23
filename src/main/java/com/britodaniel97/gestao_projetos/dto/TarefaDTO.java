package com.britodaniel97.gestao_projetos.dto;

import java.util.UUID;

public record TarefaDTO(
        UUID id,
        String title,
        String description,
        String assignedToName,
        String projetoName,
        String status
) {}
