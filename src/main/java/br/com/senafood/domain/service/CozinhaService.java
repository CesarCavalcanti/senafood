package br.com.senafood.domain.service;

import br.com.senafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.senafood.domain.exception.EntidadeEmUsoException;
import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.model.Cozinha;
import br.com.senafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    public static final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha de codigo %d nao existe";
    public static final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d esta em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar (Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id) {
        try{
            cozinhaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id)
            );
        }
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() ->
                new CozinhaNaoEncontradaException(id));
    }

    public Cozinha atualizar(Cozinha novaCozinha, Cozinha cozinhaAntiga){
        BeanUtils.copyProperties(novaCozinha,cozinhaAntiga,"id");
        return cozinhaRepository.save(cozinhaAntiga);
    }
}
