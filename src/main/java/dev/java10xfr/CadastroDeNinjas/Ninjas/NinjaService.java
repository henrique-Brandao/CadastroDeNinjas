package dev.java10xfr.CadastroDeNinjas.Ninjas;

import dev.java10xfr.CadastroDeNinjas.CadastroDeMissoes.MissoesModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> listNinja = ninjaRepository.findAll();
        return listNinja.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    public NinjaDTO listarNinjasId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId
                .map(ninjaMapper::map)
                .orElse(null);
    }

    public NinjaDTO criarNinja(NinjaDTO ninja) {
        NinjaModel ninjaModel = ninjaMapper.map(ninja);
        ninjaModel = ninjaRepository.save(ninjaModel);
        return ninjaMapper.map(ninjaModel);
    }

    public void deletarNinja(Long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {

        NinjaModel ninjaExistente = ninjaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id do ninja não encontrado"));

        if (ninjaDTO.getEmail() != null) ninjaExistente.setEmail(ninjaDTO.getEmail());
        if (ninjaDTO.getIdade() == 0) ninjaExistente.setIdade(ninjaDTO.getIdade());
        if (ninjaDTO.getImgUrl() != null) ninjaExistente.setImgUrl(ninjaDTO.getImgUrl());
        if (ninjaDTO.getRank() != null) ninjaExistente.setRank(ninjaDTO.getRank());

        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaExistente);
        return ninjaMapper.map(ninjaSalvo);
    }
}
