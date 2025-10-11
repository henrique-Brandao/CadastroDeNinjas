package dev.java10xfr.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Ninjas", description = "Operacões relacionadas a ninjas")
@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    @PostMapping()
    @Operation(summary = "Criar ninja", description = "Essa rota é responsavel por criar os ninjas e inserir no db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<NinjaDTO> criarNinja(
            @Parameter(description = "Usuario manda o ninja")
            @RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaSalvo = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaSalvo);
    }


    @GetMapping()
    @Operation(summary = "Listar todos os ninjas", description = "Essa rota é responsavel por listar todas os ninjas, caso n tenha nenhum, statuscode = 204")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso!"),
            @ApiResponse(responseCode = "204", description = "A lista de ninjas está vazia")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> listNinja = ninjaService.listarNinjas();
        if(listNinja.isEmpty()) return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
        return ResponseEntity.ok(listNinja);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Selecionar ninja por id", description = "Essa rota é responsavel por por selecionar o ninja de acordo com o id dele")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja Listado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")
    })
    public ResponseEntity<?> listarNinjasId(
            @Parameter(description = "Usuario manda o id no caminho da requisição", example = "1")
            @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasId(id);
        if (ninja == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));

        return ResponseEntity.ok(ninja);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ninja", description = "Essa rota é responsavel por receber os dados dos ninjas e irá atualiza-los e enviar pro db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")

    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuario manda o id no caminho da requisição", example = "1")
            @PathVariable Long id,
            @RequestBody NinjaDTO ninjaDTO) {

        NinjaDTO ninjaExistente = ninjaService.listarNinjasId(id);
        if(ninjaExistente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));

        NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id, ninjaDTO);
            return ResponseEntity.ok(ninjaAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um ninja", description = "Essa rota é responsavel pro deletar os ninjas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "O id informado não existe")
    })
    public ResponseEntity<String> deletarNinja(
            @Parameter(description = "Usuario manda o id no caminho da requisição para deletar", example = "1")
            @PathVariable Long id) {
        NinjaDTO possibleNinja = ninjaService.listarNinjasId(id);
        if (possibleNinja == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));

        ninjaService.deletarNinja(id);
        return ResponseEntity.ok(String.format("Ninja com o id: %d deletado", id));
    }
}
