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

    // Criar Ninja (CREATE)
    @PostMapping("/criar") // POST -- Mandar uma requisição para Criar os ninjas
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.criarNinja(ninja);
    }

    // Listar todos os Ninjas (READ)
    @GetMapping("/listar") // GET -- Mandar uma requisição para mostrar os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Listar todos os Ninjas por id(READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasId(@PathVariable Long id) {
        return ninjaService.listarNinjasId(id);
    }

    // Atualizar/Editar Ninjas
    @PutMapping("/atualizar") // PUT -- Mandar uma requisição para atualizar os ninjas
    public String atualizarNinja() {
        return "Atualizar Ninja";
    }

    // Remover Ninjas
    @DeleteMapping("/deletar")  // DELETE -- Mandar uma requisição para deletar os ninjas
    public String deletarNinja() {
        return "Deletar Ninja";
    }
}
