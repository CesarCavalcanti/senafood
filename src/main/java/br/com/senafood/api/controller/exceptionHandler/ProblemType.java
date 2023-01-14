package br.com.senafood.api.controller.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivo"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

    private String uri;
    private String title;


    ProblemType( String uri,String title) {
        this.uri = "https://senafood.com.br" + uri;
        this.title = title;
    }
}
