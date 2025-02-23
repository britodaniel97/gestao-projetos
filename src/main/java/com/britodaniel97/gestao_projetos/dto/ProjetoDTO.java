package com.britodaniel97.gestao_projetos.dto;

import java.util.List;
import java.util.UUID;

public record ProjetoDTO(
        UUID id,
        String name,
        String description,
        String managerName,
        String status,
        List<TarefaDTO> tarefas
) {}
