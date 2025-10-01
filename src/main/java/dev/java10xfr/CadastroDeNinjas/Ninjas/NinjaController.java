package dev.java10xfr.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    // Criar Ninja (CREATE)
    @PostMapping("/criar") // POST -- Mandar uma requisição para Criar os ninjas
    public String criarNinja() {
        return "criar Ninja";
    }

    // Listar todas os Ninjas (READ)
    @GetMapping("/listar") // GET -- Mandar uma requisição para mostrar os ninjas
    public String listarNinjas() {
        return "listar todas os Ninjas";
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
