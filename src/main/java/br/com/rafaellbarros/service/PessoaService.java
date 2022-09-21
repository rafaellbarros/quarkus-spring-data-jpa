package br.com.rafaellbarros.service;

import br.com.rafaellbarros.model.entity.Pessoa;
import br.com.rafaellbarros.repository.PessoaRepository;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
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

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa inserir(final Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
