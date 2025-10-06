package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Listar todas as missões
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Listar missão por id
    public MissoesModel listarMissoesId(Long id) {
        Optional<MissoesModel> missaoId = missoesRepository.findById(id);
        return missaoId.orElse(null);
    }

    // Criar missoes
    public MissoesModel criarMissoao(MissoesModel missoesModel) {
        return missoesRepository.save(missoesModel);
    }

    // Deletar missao
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}
