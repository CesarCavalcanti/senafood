package br.com.senafood.domain.service;

import br.com.senafood.domain.exception.EntidadeEmUsoException;
import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.exception.RestauranteNaoEncontradoException;
import br.com.senafood.domain.model.Cozinha;
import br.com.senafood.domain.model.Restaurante;
import br.com.senafood.domain.repository.restaurante.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de codigo %d esta em uso";
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaService cozinhaService;

    public Restaurante salvar (Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long id) {
        try{
            restauranteRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_RESTAURANTE_EM_USO, id)
            );
        }
    }

    public Restaurante atualizar(Restaurante novoRestaurante,Restaurante antigoRestaurante){
        Long cozinhaIdNovo = novoRestaurante.getCozinha().getId();

        Cozinha cozinhaNova = cozinhaService.buscarOuFalhar(cozinhaIdNovo);

        BeanUtils.copyProperties(novoRestaurante,antigoRestaurante,"id","formasPagamento","endereco", "dataCadastro");
        antigoRestaurante.setCozinha(cozinhaNova);
        return restauranteRepository.save(antigoRestaurante);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() ->
                new RestauranteNaoEncontradoException(id));
    }
}
