package dev.java10xfr.CadastroDeNinjas.Ninjas;

import dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Entity transforma uma classe em uma entidade no dataBase
@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    //Uma unica missão pra cada ninja
    @ManyToOne
    @JoinColumn(name = "Missoes_id") // Foreing key ou chave estrangeira
    private MissoesModel missoes;
}
