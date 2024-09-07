package com.example.projetoexemploreactivespring.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("produtos")
public class Produto {

    @Id
    private Long Id;
    private String nome;
    private double preco;


}
