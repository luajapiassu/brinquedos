package com.loja.brinquedos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TDS_TB_BRINQUEDOS")
@SequenceGenerator(name = "brinq", sequenceName = "SQ_TDS_TB_BRINQUEDOS", allocationSize = 1)
public class Brinquedo {
    @Id
    @Column(name = "cd_brinquedo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brinq")
    private Long id;

    @Column(name = "nm_brinquedo", nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_tipo", nullable = false, length = 40)
    private TipoBrinquedo tipo;

    @Column(name = "ds_classificacao", nullable = false, length = 20)
    private String classificacao;

    @Column(name = "ds_tamanho", nullable = false, length = 20)
    private String tamanho;

    @Column(name ="vl_preco", nullable = false)
    private Double preco;
}
