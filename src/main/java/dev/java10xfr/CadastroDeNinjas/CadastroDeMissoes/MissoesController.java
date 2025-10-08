package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missao")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoesModel) {
        return missoesService.criarMissoao(missoesModel);
    }

    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesDTO listarMissaoId(@PathVariable Long id) {
        return missoesService.listarMissoesId(id);
    }

    // Atualizar/Editar missões
    @PutMapping("/atualizar/{id}") // PUT -- Mandar uma requisição para alterar as missões
    public MissoesDTO atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao) {
        return missoesService.editarMissoes(id, missao);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
    }
}
