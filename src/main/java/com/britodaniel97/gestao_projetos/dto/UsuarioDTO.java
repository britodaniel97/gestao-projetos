package com.britodaniel97.gestao_projetos.dto;

import java.util.UUID;

public record UsuarioDTO(
        UUID id,
        String name,
        String email,
        String role
) {}
