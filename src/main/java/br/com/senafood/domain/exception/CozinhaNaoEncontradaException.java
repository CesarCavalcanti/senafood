package br.com.senafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format("Cozinha de codigo %d nao existe", cozinhaId));
    }
}
