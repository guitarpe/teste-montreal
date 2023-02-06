package br.montreal.application.repository;

import br.montreal.application.model.Bem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BemRepository extends JpaRepository<Bem, Long> {
}
