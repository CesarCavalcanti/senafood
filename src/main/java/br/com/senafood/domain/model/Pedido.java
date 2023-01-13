package br.com.senafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @Embedded
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(columnDefinition = "datetime")
    private LocalDateTime dataConfirmacao;

    @Column(columnDefinition = "datetime")
    private LocalDateTime dataCancelamento;

    @Column(columnDefinition = "datetime")
    private LocalDateTime dataEntrega;

    private StatusPedido status;

}
