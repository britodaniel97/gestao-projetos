package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.dto.TarefaDTO;
import com.britodaniel97.gestao_projetos.entity.Tarefa;
import com.britodaniel97.gestao_projetos.repository.TarefaRepository;
import com.britodaniel97.gestao_projetos.mapper.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaDTO> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(DTOConverter::convertTarefaToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TarefaDTO> buscarTarefaPorId(UUID id) {
        return tarefaRepository.findById(id)
                .map(DTOConverter::convertTarefaToDTO);
    }

    public TarefaDTO criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = DTOConverter.convertDTOToTarefa(tarefaDTO);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return DTOConverter.convertTarefaToDTO(tarefaSalva);
    }

    public Optional<TarefaDTO> atualizarTarefa(UUID id, TarefaDTO tarefaDTO) {
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setTitle(tarefaDTO.title());
            tarefaExistente.setDescription(tarefaDTO.description());
            if (tarefaDTO.status() != null) {
                tarefaExistente.setStatus(com.britodaniel97.gestao_projetos.enums.TaskStatus.valueOf(tarefaDTO.status()));
            }
            Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);
            return DTOConverter.convertTarefaToDTO(tarefaAtualizada);
        });
    }

    public boolean deletarTarefa(UUID id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
