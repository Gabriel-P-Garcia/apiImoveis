package br.com.atividade.api_imoveis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table (name = "tb_imovel")
@Getter
@Setter

public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", length = 80, nullable = false)
    private String nome;

    @Column (name = "valor", precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_proprietario", nullable = false)
    private Proprietario proprietario;
}
