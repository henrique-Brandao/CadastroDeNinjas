package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/missao")
public class MissoesController {

    private final MissoesService missoesService;

    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missaoCriada = missoesService.criarMissoao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missaoCriada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> listMissoes = missoesService.listarMissoes();
        if(listMissoes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(listMissoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoId(@PathVariable Long id) {
        MissoesDTO missaoExistente = missoesService.listarMissoesId(id);
        if (missaoExistente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        return ResponseEntity.ok(missaoExistente);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao) {
        MissoesDTO missaoAtualizada = missoesService.editarMissoes(id, missao);
        if(missaoAtualizada == null)return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        return ResponseEntity.ok(missaoAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {
        MissoesDTO missaoExistente = missoesService.listarMissoesId(id);
        if(missaoExistente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        missoesService.deletarMissao(id);
        return ResponseEntity.ok(String.format("Missao %s com o ID: %d deletado com sucesso", missaoExistente.getNome(),missaoExistente.getId()));

    }
}
