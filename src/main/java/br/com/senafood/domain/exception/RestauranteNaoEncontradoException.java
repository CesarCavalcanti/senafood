package br.com.senafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format("Restaurante de codigo %d nao existe", restauranteId));
    }
}
