package br.com.senafood.api.controller.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivo"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String title, String uri) {
        this.title = title;
        this.uri = "https://senafood.com.br" + uri;
    }
}
