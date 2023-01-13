package br.com.senafood.api.controller;

import br.com.senafood.domain.model.Estado;
import br.com.senafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

   @Autowired
   private EstadoService estadoService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Estado adicionar (@RequestBody Estado estado){
      return estadoService.salvar(estado);
   }

   @GetMapping
   public List<Estado> listar(){
      return estadoService.listar();
   }

   @GetMapping("/{id}")
   public Estado buscar(@PathVariable("id") Long id){
     return estadoService.buscarOuFalhar(id);
   }

   @PutMapping("/{id}")
   public Estado atualizar (@PathVariable Long id, @RequestBody Estado novoEstado){
      Estado estadoAtual = estadoService.buscarOuFalhar(id);
      return estadoService.atualizar(novoEstado,estadoAtual);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void remover (@PathVariable Long id){
      estadoService.excluir(id);
   }
}
