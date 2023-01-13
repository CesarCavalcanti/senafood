package br.com.senafood.domain.repository.restaurante;

import br.com.senafood.domain.model.Restaurante;
import br.com.senafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante,Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor {

    @Query("FROM Restaurante  r JOIN FETCH r.cozinha") //Resolvendo problema n +1
    List<Restaurante> findAll();

}
