package br.montreal.application.repository;

import br.montreal.application.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByPessoaNome(String title);
}
