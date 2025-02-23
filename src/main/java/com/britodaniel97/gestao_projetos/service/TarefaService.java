package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.dto.TarefaDTO;
import com.britodaniel97.gestao_projetos.entity.Tarefa;
import com.britodaniel97.gestao_projetos.mapper.TarefaMapper;
import com.britodaniel97.gestao_projetos.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public List<TarefaDTO> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefaMapper.toDTOList(tarefas);
    }

    public Optional<TarefaDTO> buscarTarefaPorId(UUID id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(tarefaMapper::toDTO);
    }

    public TarefaDTO criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefaSalva);
    }

    public Optional<TarefaDTO> atualizarTarefa(UUID id, TarefaDTO tarefaDTO) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
            tarefa.setId(id);
            Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
            return Optional.of(tarefaMapper.toDTO(tarefaAtualizada));
        }
        return Optional.empty();
    }

    public boolean deletarTarefa(UUID id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
