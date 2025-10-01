package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    // Criar Missão (CREATE)
    @PostMapping("/criar") // POST -- Mandar uma requisição para criar as missões
    public String criarMissao() {
        return "criar missão";
    }

    // Listar todas as missões (READ)
    @GetMapping("/listar") // GET -- Mandar uma requisição para mostrar as missões
    public String listarMissoes() {
        return "listar todas as missões";
    }

    // Listar  missão por id (READ)
    @GetMapping("/listarid")
    public String listarMissaoId() {
        return "Listar missões pelo ID";
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
