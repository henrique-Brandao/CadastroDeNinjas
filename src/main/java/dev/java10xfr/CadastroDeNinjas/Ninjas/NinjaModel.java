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
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "rank")
    private String rank;

    //Uma unica missão pra cada ninja
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreing key ou chave estrangeira
    private MissoesModel missoes;
}
