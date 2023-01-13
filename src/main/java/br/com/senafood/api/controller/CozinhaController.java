package br.com.senafood.api.controller;

import br.com.senafood.domain.model.Cozinha;
import br.com.senafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adiconar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaService.listar();
    }

    @GetMapping("/{id}")
    public Cozinha buscar (@PathVariable("id") Long id){
        return cozinhaService.buscarOuFalhar(id);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar (@PathVariable Long id, @RequestBody Cozinha novaCozinha){
        Cozinha cozinhaAtual =  cozinhaService.buscarOuFalhar(id);
        return cozinhaService.atualizar(novaCozinha,cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long id){
        cozinhaService.excluir(id);
    }
}
