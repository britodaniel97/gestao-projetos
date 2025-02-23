package com.britodaniel97.gestao_projetos.entity;

import com.britodaniel97.gestao_projetos.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tarefas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private Usuario assignedTo;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}