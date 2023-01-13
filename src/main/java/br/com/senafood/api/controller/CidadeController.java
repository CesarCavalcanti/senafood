package br.com.senafood.api.controller;

import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.exception.EstadoNaoEncontradoException;
import br.com.senafood.domain.exception.NegocioException;
import br.com.senafood.domain.model.Cidade;
import br.com.senafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeService.listar();
    }

    @GetMapping("/{id}")
    public Cidade buscar (@PathVariable("id") Long id){
        return cidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar (@PathVariable Long id, @RequestBody Cidade novaCidade){
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
        try{
            return cidadeService.atualizar(novaCidade,cidadeAtual);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
