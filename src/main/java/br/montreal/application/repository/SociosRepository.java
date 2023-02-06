package br.montreal.application.repository;

import br.montreal.application.model.Socios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SociosRepository extends JpaRepository<Socios, Long> {
    void deleteAllByEmpresa(long id);
    void deleteSociosByIdAndEmpresa(long idSocio, long idEmpresa);
    Optional<Socios> findByIdAndEmpresa(long idSocio, long idEmpresa);

    @Query("select s from Socios s where s.empresa.pessoa.nome=:nomeEmpresa")
    List<Socios> buscarSociosPorNomeEmpresa(@Param("nomeEmpresa") String nome);
}
