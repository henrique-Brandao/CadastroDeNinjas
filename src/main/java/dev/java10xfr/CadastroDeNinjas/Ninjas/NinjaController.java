package dev.java10xfr.CadastroDeNinjas.Ninjas;

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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.criarNinja(ninja);
    }


    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjasId(@PathVariable Long id) {
        return ninjaService.listarNinjasId(id);
    }

    // Atualizar/Editar Ninjas
    @PutMapping("/atualizar/{id}") // PUT -- Mandar uma requisição para atualizar os ninjas
    public NinjaDTO atualizarNinja(@PathVariable Long id,@RequestBody NinjaDTO ninjaModel) {
        return ninjaService.atualizarNinja(id, ninjaModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinja(id);
    }
}
