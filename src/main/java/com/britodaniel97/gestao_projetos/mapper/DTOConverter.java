package com.britodaniel97.gestao_projetos.util;

import com.britodaniel97.gestao_projetos.dto.ProjetoDTO;
import com.britodaniel97.gestao_projetos.dto.TarefaDTO;
import com.britodaniel97.gestao_projetos.dto.UsuarioDTO;
import com.britodaniel97.gestao_projetos.entity.Projeto;
import com.britodaniel97.gestao_projetos.entity.Tarefa;
import com.britodaniel97.gestao_projetos.entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    public static TarefaDTO convertTarefaToDTO(Tarefa tarefa) {
        if (tarefa == null) {
            return null;
        }
        String assignedToName = (tarefa.getAssignedTo() != null) ? tarefa.getAssignedTo().getName() : null;
        String projetoName = (tarefa.getProjeto() != null) ? tarefa.getProjeto().getName() : null;
        String status = (tarefa.getStatus() != null) ? tarefa.getStatus().name() : null;
        return new TarefaDTO(
                tarefa.getId(),
                tarefa.getTitle(),
                tarefa.getDescription(),
                assignedToName,
                projetoName,
                status
        );
    }

    public static List<TarefaDTO> convertTarefaListToDTO(List<Tarefa> tarefas) {
        return tarefas.stream()
                .map(DTOConverter::convertTarefaToDTO)
                .collect(Collectors.toList());
    }

    // Conversão de Projeto para ProjetoDTO
    public static ProjetoDTO convertProjetoToDTO(Projeto projeto) {
        if (projeto == null) {
            return null;
        }
        String managerName = (projeto.getManager() != null) ? projeto.getManager().getName() : null;
        String status = (projeto.getStatus() != null) ? projeto.getStatus().name() : null;
        List<TarefaDTO> tarefasDTO = (projeto.getTarefas() != null) ? convertTarefaListToDTO(projeto.getTarefas()) : null;
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getName(),
                projeto.getDescription(),
                managerName,
                status,
                tarefasDTO
        );
    }

    public static List<ProjetoDTO> convertProjetoListToDTO(List<Projeto> projetos) {
        return projetos.stream()
                .map(DTOConverter::convertProjetoToDTO)
                .collect(Collectors.toList());
    }


    public static UsuarioDTO convertUsuarioToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        String role = (usuario.getRole() != null) ? usuario.getRole().name() : null;
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                role
        );
    }

    public static List<UsuarioDTO> convertUsuarioListToDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(DTOConverter::convertUsuarioToDTO)
                .collect(Collectors.toList());
    }


    public static Tarefa convertDTOToTarefa(TarefaDTO tarefaDTO) {
        if (tarefaDTO == null) {
            return null;
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setId(tarefaDTO.id());
        tarefa.setTitle(tarefaDTO.title());
        tarefa.setDescription(tarefaDTO.description());

        return tarefa;
    }

    public static Projeto convertDTOToProjeto(ProjetoDTO projetoDTO) {
        if (projetoDTO == null) {
            return null;
        }
        Projeto projeto = new Projeto();
        projeto.setId(projetoDTO.id());
        projeto.setName(projetoDTO.name());
        projeto.setDescription(projetoDTO.description());

        return projeto;
    }

    public static Usuario convertDTOToUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.id());
        usuario.setName(usuarioDTO.name());
        usuario.setEmail(usuarioDTO.email());
        if (usuarioDTO.role() != null) {
            usuario.setRole(com.britodaniel97.gestao_projetos.enums.UserRole.valueOf(usuarioDTO.role()));
        }
        return usuario;
    }
}
