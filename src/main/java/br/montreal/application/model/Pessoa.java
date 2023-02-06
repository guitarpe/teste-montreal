package br.montreal.application.model;

import br.montreal.application.model.enuns.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo", nullable = false)
    private TipoPessoa tipo;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="endereco_id")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Bem> bensImoveis;
}
