package br.com.rafaellbarros.repository;

import br.com.rafaellbarros.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 21/09/2022
 */

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
