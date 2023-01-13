package br.com.senafood.api.controller;

import br.com.senafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.senafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.senafood.domain.exception.NegocioException;
import br.com.senafood.domain.model.Restaurante;
import br.com.senafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public Restaurante buscar (@PathVariable("id") Long id){
       return restauranteService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try{
            return restauranteService.salvar(restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar (@PathVariable Long id, @RequestBody Restaurante novoRestaurante){
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
        try{
            return restauranteService.atualizar(novoRestaurante,restauranteAtual);
        }catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id,@RequestBody Map<String, Object> campos){
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
        merge(campos,restauranteAtual);
        return atualizar(id,restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
