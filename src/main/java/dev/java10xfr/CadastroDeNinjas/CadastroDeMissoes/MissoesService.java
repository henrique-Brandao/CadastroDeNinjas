package dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes;

import dev.java10xfr.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes() {
       List<MissoesModel> missoesModels = missoesRepository.findAll();
        return missoesModels.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO listarMissoesId(Long id) {
        Optional<MissoesModel> missaoId = missoesRepository.findById(id);
        return missaoId
                .map(missoesMapper::map)
                .orElse(null);

    }

    // Criar missoes
    public MissoesDTO criarMissoao(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel = missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);
    }

    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

    // Editar missoes
    public MissoesDTO editarMissoes(Long id, MissoesDTO missao) {
        Optional<MissoesModel> missaoExiste = missoesRepository.findById(id);
        if (missaoExiste.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missao);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
