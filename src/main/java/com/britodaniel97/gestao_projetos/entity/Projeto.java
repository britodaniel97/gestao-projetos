package com.britodaniel97.gestao_projetos.entity;

import com.britodaniel97.gestao_projetos.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "projetos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Usuario manager;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;
}
