package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;

import dev.java10xfr.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10xfr.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missao")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // Criar Missão (CREATE)
    @PostMapping("/criar") // POST -- Mandar uma requisição para criar as missões
    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel) {
        return missoesService.criarMissoao(missoesModel);
    }

    // Listar todas as missões (READ)
    @GetMapping("/listar") // GET -- Mandar uma requisição para mostrar as missões
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // Listar  missão por id (READ)
    @GetMapping("/listar/{id}")
    public MissoesModel listarMissaoId(@PathVariable Long id) {
        return missoesService.listarMissoesId(id);
    }

    // Atualizar/Editar missões
    @PutMapping("/atualizar") // PUT -- Mandar uma requisição para alterar as missões
    public String atualizarMissao() {
        return "Atualizar missões";
    }

    // Remover missões
    @DeleteMapping("/deletar") // DELETE -- Mandar uma requisição para deletar as missões
    public String deletarMissao() {
        return "Deletar missões";
    }
}
