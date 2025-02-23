package com.britodaniel97.gestao_projetos.mapper;

import com.britodaniel97.gestao_projetos.dto.TarefaDTO;
import com.britodaniel97.gestao_projetos.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(source = "projeto.id", target = "projetoId")
    @Mapping(source = "projeto.name", target = "projetoName")
    @Mapping(source = "assignedTo.name", target = "assignedToName")
    TarefaDTO toDTO(Tarefa tarefa);

    List<TarefaDTO> toDTOList(List<Tarefa> tarefas);

    @Mapping(source = "projetoId", target = "projeto.id")
    @Mapping(source = "assignedToName", target = "assignedTo.name")
    @Mapping(source = "projetoName", target = "projeto.name")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    List<Tarefa> toEntityList(List<TarefaDTO> tarefaDTOs);
}
