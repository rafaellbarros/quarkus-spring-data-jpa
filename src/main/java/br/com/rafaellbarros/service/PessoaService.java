package br.com.rafaellbarros.service;

import br.com.rafaellbarros.model.entity.PessoaEntity;
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

    public List<PessoaEntity> listarTodas() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public PessoaEntity inserir(final PessoaEntity pessoaEntity) {
        return pessoaRepository.save(pessoaEntity);
    }
}
