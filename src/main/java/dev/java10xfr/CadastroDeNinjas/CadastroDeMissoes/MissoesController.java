package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/missao")
@Tag(name = "Missoes", description = "Operações relacionadas a missões")
public class MissoesController {

    private final MissoesService missoesService;


    @PostMapping("/criar")
    @Operation(summary = "Criar missao", description = "Essa rota é responsavel por criar as missoes e inserir no db" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possivel crirar a missao!")
    })
    public ResponseEntity<MissoesDTO> criarMissao(
            @Parameter(description = "Usuario manda a missao")
            @RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missaoCriada = missoesService.criarMissoao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(missaoCriada);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as missoes", description = "Essa rota é responsavel por listar todas as missoes, caso n tenha nenhum, statuscode = 204")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missoes listadas com sucesso!"),
            @ApiResponse(responseCode = "204", description = "A lista de missoes está vazia!")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> listMissoes = missoesService.listarMissoes();
        if(listMissoes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(listMissoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "selecionar missao por id", description = "Essa rota é responsavel por por selecionar a missao de acordo com o id dele")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")
    })
    public ResponseEntity<?> listarMissaoId(
            @Parameter(description = "Usuario manda o id no caminho da requisição", example = "1")
            @PathVariable Long id) {
        MissoesDTO missaoExistente = missoesService.listarMissoesId(id);
        if (missaoExistente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        return ResponseEntity.ok(missaoExistente);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar missão", description = "Essa rota é responsavel por receber os dados da missao e irá atualiza-los e enviar pro db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")
    })
    public ResponseEntity<?> atualizarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisição", example = "2")
            @PathVariable Long id, @RequestBody MissoesDTO missao) {
        MissoesDTO missaoAtualizada = missoesService.editarMissoes(id, missao);
        if(missaoAtualizada == null)return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        return ResponseEntity.ok(missaoAtualizada);
    }


    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar missao", description = "Essa rota é responsavel pro deletar as missoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisição para deletar", example = "3")
            @PathVariable Long id) {
        MissoesDTO missaoExistente = missoesService.listarMissoesId(id);
        if(missaoExistente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id informado não encontrado");
        missoesService.deletarMissao(id);
        return ResponseEntity.ok(String.format("Missao %s com o ID: %d deletado com sucesso", missaoExistente.getNome(),missaoExistente.getId()));

    }
}
