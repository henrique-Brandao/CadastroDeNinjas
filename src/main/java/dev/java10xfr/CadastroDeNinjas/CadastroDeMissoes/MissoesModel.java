package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;


import dev.java10xfr.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dificuldade_missao", nullable = false)
    private String dificuldade;

    //Uma missão vai ter uma lista de ninjas / Varios Ninjas
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;

}
