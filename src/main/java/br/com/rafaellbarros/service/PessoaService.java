package br.com.rafaellbarros.service;

import br.com.rafaellbarros.model.dto.PessoaDTO;
import br.com.rafaellbarros.model.mapper.PessoaMapper;
import br.com.rafaellbarros.repository.PessoaRepository;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 21/09/2022
 */

@ApplicationScoped
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper mapper;

    public List<PessoaDTO> listarTodas() {
        return mapper.toDTO(pessoaRepository.findAll());
    }

    public PessoaDTO obterPorId(final Long id) {
        return mapper.toDTO(pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!")));
    }

    @Transactional
    public PessoaDTO inserir(final PessoaDTO pessoaDTO) {
        return mapper.toDTO(pessoaRepository.save(mapper.toEntity(pessoaDTO)));
    }
}
