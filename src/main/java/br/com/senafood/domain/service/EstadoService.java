package br.com.senafood.domain.service;

import br.com.senafood.domain.exception.EntidadeEmUsoException;
import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.exception.EstadoNaoEncontradoException;
import br.com.senafood.domain.model.Estado;
import br.com.senafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    public static final String MSG_ESTADO_EM_USO = "Estado de codigo %d esta em uso";
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar (Estado estado){
        return estadoRepository.save(estado);
    }

    public void excluir(Long id) {
        try{
            estadoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, id)
            );
        }
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id).orElseThrow(() ->
                new EstadoNaoEncontradoException(id));
    }

    public Estado atualizar(Estado novoEstado, Estado estadoAntigo) {
        BeanUtils.copyProperties(novoEstado,estadoAntigo,"id");
        return estadoRepository.save(estadoAntigo);
    }
}
