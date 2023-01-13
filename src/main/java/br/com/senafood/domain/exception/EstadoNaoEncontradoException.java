package br.com.senafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format("Estado de codigo %d nao existe", estadoId));
    }
}
