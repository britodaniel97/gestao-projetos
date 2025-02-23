package com.britodaniel97.gestao_projetos.controller;

import com.britodaniel97.gestao_projetos.dto.TarefaDTO;
import com.britodaniel97.gestao_projetos.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable UUID id) {
        return tarefaService.buscarTarefaPorId(id)
                .map(tarefaDTO -> ResponseEntity.ok(tarefaDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.criarTarefa(tarefaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable UUID id, @RequestBody TarefaDTO tarefaDTO) {
        return tarefaService.atualizarTarefa(id, tarefaDTO)
                .map(tarefaDTOAtualizada -> ResponseEntity.ok(tarefaDTOAtualizada))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id) {
        return tarefaService.deletarTarefa(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
