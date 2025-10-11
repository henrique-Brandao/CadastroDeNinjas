package dev.java10xfr.CadastroDeNinjas.Ninjas;

import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    @PostMapping("/criar")
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaSalvo = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaSalvo);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> listNinja = ninjaService.listarNinjas();
        if(listNinja.isEmpty()) return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
        return ResponseEntity.ok(listNinja);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasId(id);
        if (ninja != null) return ResponseEntity.ok(ninja);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id,@RequestBody NinjaDTO ninjaDTO) {

        NinjaDTO ninjaExistente = ninjaService.listarNinjasId(id);
        if(ninjaExistente != null) {
        NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id, ninjaDTO);
            return ResponseEntity.ok(ninjaAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id, ServletRequest servletRequest) {
        NinjaDTO possibleNinja = ninjaService.listarNinjasId(id);
        if (possibleNinja != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok(String.format("Ninja com o id: %d deletado", id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Não foi encontrado um ninja com o id: %d", id));
    }
}
