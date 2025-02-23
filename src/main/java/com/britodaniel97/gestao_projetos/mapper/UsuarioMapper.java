package com.britodaniel97.gestao_projetos.mapper;

import com.britodaniel97.gestao_projetos.dto.UsuarioDTO;
import com.britodaniel97.gestao_projetos.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTOs);
}
