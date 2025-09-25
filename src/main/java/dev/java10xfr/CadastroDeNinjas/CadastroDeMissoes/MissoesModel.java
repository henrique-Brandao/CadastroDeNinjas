package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;


import dev.java10xfr.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    //Uma missão vai ter uma lista de ninjas / Varios Ninjas
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninja;



}
