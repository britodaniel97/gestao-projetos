package com.britodaniel97.gestao_projetos.mapper;

import com.britodaniel97.gestao_projetos.dto.ProjetoDTO;
import com.britodaniel97.gestao_projetos.entity.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    @Mapping(source = "tarefas", target = "tarefas")
    ProjetoDTO toDTO(Projeto projeto);

    List<ProjetoDTO> toDTOList(List<Projeto> projetos);

    @Mapping(source = "tarefas", target = "tarefas")
    Projeto toEntity(ProjetoDTO projetoDTO);

    List<Projeto> toEntityList(List<ProjetoDTO> projetoDTOs);
}
