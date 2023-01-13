package br.com.senafood.domain.repository;

import br.com.senafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {

}
