package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.dto.ProjetoDTO;
import com.britodaniel97.gestao_projetos.entity.Projeto;
import com.britodaniel97.gestao_projetos.mapper.ProjetoMapper;
import com.britodaniel97.gestao_projetos.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    public ProjetoService(ProjetoRepository projetoRepository, ProjetoMapper projetoMapper) {
        this.projetoRepository = projetoRepository;
        this.projetoMapper = projetoMapper;
    }

    public List<ProjetoDTO> listarProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();
        return projetoMapper.toDTOList(projetos);
    }

    public Optional<ProjetoDTO> buscarProjetoPorId(UUID id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.map(projetoMapper::toDTO);
    }

    public ProjetoDTO criarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        Projeto projetoSalvo = projetoRepository.save(projeto);
        return projetoMapper.toDTO(projetoSalvo);
    }

    public Optional<ProjetoDTO> atualizarProjeto(UUID id, ProjetoDTO projetoDTO) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);
        if (projetoExistente.isPresent()) {
            Projeto projeto = projetoMapper.toEntity(projetoDTO);
            projeto.setId(id);
            Projeto projetoAtualizado = projetoRepository.save(projeto);
            return Optional.of(projetoMapper.toDTO(projetoAtualizado));
        }
        return Optional.empty();
    }

    public boolean deletarProjeto(UUID id) {
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
