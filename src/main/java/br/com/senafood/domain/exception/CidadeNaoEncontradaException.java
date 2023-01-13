package br.com.senafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format("Cidade de codigo %d nao existe", cidadeId));
    }
}
