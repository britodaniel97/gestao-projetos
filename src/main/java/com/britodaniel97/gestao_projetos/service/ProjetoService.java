package com.britodaniel97.gestao_projetos.service;

import com.britodaniel97.gestao_projetos.entity.Projeto;
import com.britodaniel97.gestao_projetos.dto.ProjetoDTO;
import com.britodaniel97.gestao_projetos.repository.ProjetoRepository;
import com.britodaniel97.gestao_projetos.mapper.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<ProjetoDTO> listarProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();
        return DTOConverter.convertProjetoListToDTO(projetos);
    }

    public Optional<ProjetoDTO> buscarProjetoPorId(UUID id) {
        return projetoRepository.findById(id)
                .map(DTOConverter::convertProjetoToDTO);
    }

    public ProjetoDTO criarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = DTOConverter.convertDTOToProjeto(projetoDTO);
        Projeto projetoSalvo = projetoRepository.save(projeto);
        return DTOConverter.convertProjetoToDTO(projetoSalvo);
    }

    public Optional<ProjetoDTO> atualizarProjeto(UUID id, ProjetoDTO projetoDTO) {
        return projetoRepository.findById(id).map(projetoExistente -> {
            projetoExistente.setName(projetoDTO.name());
            projetoExistente.setDescription(projetoDTO.description());
            if (projetoDTO.status() != null) {
                projetoExistente.setStatus((projetoDTO.status()));
            }
            Projeto projetoAtualizado = projetoRepository.save(projetoExistente);
            return DTOConverter.convertProjetoToDTO(projetoAtualizado);
        });
    }

    public boolean deletarProjeto(UUID id) {
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
