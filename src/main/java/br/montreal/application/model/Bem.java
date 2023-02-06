package br.montreal.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bens_imoveis")
public class Bem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;
}
