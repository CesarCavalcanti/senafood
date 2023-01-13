package br.com.senafood.domain.service;

import br.com.senafood.domain.exception.CidadeNaoEncontradaException;
import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.exception.EstadoNaoEncontradoException;
import br.com.senafood.domain.model.Cidade;
import br.com.senafood.domain.model.Estado;
import br.com.senafood.domain.repository.CidadeRepository;
import br.com.senafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar (Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Cidade novaCidade, Cidade antigaCidade){
        Long estadoIdNovo = novaCidade.getEstado().getId();

        Estado novoEstado = estadoRepository.findById(estadoIdNovo)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoIdNovo));

        BeanUtils.copyProperties(novaCidade,antigaCidade,"id");
        antigaCidade.setEstado(novoEstado);
        return cidadeRepository.save(antigaCidade);
    }
    public Cidade buscarOuFalhar(Long id){
        return cidadeRepository.findById(id).orElseThrow(() ->
                new CidadeNaoEncontradaException(id));
    }

    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }
}
