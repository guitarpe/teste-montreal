package br.montreal.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empresa")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id")
    private List<Socios> socios;

    @Column(name = "compromentimento", nullable = false)
    private Double comprometimentoFinanceiro;
}
