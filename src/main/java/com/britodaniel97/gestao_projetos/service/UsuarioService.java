package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.dto.UsuarioDTO;
import com.britodaniel97.gestao_projetos.entity.Usuario;
import com.britodaniel97.gestao_projetos.mapper.UsuarioMapper;
import com.britodaniel97.gestao_projetos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDTOList(usuarios);
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioMapper::toDTO);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioSalvo);
    }

    public Optional<UsuarioDTO> atualizarUsuario(UUID id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
            usuario.setId(id);
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return Optional.of(usuarioMapper.toDTO(usuarioAtualizado));
        }
        return Optional.empty();
    }

    public boolean deletarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
