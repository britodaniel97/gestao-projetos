package com.britodaniel97.gestao_projetos.controller;

import com.britodaniel97.gestao_projetos.dto.UsuarioDTO;
import com.britodaniel97.gestao_projetos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable UUID id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(usuarioDTO -> ResponseEntity.ok(usuarioDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.criarUsuario(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.atualizarUsuario(id, usuarioDTO)
                .map(usuarioDTOAtualizado -> ResponseEntity.ok(usuarioDTOAtualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        return usuarioService.deletarUsuario(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
