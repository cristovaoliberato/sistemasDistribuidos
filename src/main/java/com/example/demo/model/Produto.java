package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @NotNull
    @NotBlank
    private String titulo;
    @NotNull
    @NotBlank
    private String descricao;
    @NotNull @DecimalMin("0.05")
    private double preco;
    @NotNull
    private int estoque;
    /**código de barras.**/
    @NotNull
    private String ean;

}
