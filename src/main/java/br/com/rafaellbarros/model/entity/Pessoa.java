package br.com.rafaellbarros.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 21/09/2022
 */

@Getter
@Setter
@Entity
public class Pessoa {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Long idade;

}
