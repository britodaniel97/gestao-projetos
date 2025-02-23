package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.dto.UsuarioDTO;
import com.britodaniel97.gestao_projetos.entity.Usuario;
import com.britodaniel97.gestao_projetos.repository.UsuarioRepository;
import com.britodaniel97.gestao_projetos.util.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(DTOConverter::convertUsuarioToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(DTOConverter::convertUsuarioToDTO);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = DTOConverter.convertDTOToUsuario(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return DTOConverter.convertUsuarioToDTO(usuarioSalvo);
    }

    public Optional<UsuarioDTO> atualizarUsuario(UUID id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setName(usuarioDTO.name());
            usuarioExistente.setEmail(usuarioDTO.email());
            if (usuarioDTO.role() != null) {
                usuarioExistente.setRole(com.britodaniel97.gestao_projetos.enums.UserRole.valueOf(usuarioDTO.role()));
            }
            Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
            return DTOConverter.convertUsuarioToDTO(usuarioAtualizado);
        });
    }

    public boolean deletarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
