package com.britodaniel97.gestao_projetos.controller;

import com.britodaniel97.gestao_projetos.dto.ProjetoDTO;
import com.britodaniel97.gestao_projetos.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> buscarProjetoPorId(@PathVariable UUID id) {
        return projetoService.buscarProjetoPorId(id)
                .map(projetoDTO -> ResponseEntity.ok(projetoDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.ok(projetoService.criarProjeto(projetoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualizarProjeto(@PathVariable UUID id, @RequestBody ProjetoDTO projetoDTO) {
        return projetoService.atualizarProjeto(id, projetoDTO)
                .map(projetoDTOAtualizado -> ResponseEntity.ok(projetoDTOAtualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable UUID id) {
        return projetoService.deletarProjeto(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
