package com.loja.brinquedos.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Column(name = "nm_brinquedo", nullable = false, length = 50)
    private String nome;

    @NotNull(message = "O tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "ds_tipo", nullable = false, length = 40)
    private TipoBrinquedo tipo;

    @NotBlank(message = "A classificação é obrigatória")
    @Size(max = 20, message = "A classificação deve ter no máximo 20 caracteres")
    @Column(name = "ds_classificacao", nullable = false, length = 20)
    private String classificacao;

    @NotBlank(message = "O tamanho é obrigatório")
    @Size(max = 20, message = "O tamanho deve ter no máximo 20 caracteres")
    @Column(name = "ds_tamanho", nullable = false, length = 20)
    private String tamanho;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    @Column(name ="vl_preco", nullable = false)
    private Double preco;
}
