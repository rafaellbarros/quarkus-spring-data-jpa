package br.com.rafaellbarros.core.model.mapper;

import java.util.List;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 26/09/2022
 */

public interface BaseMapper<E, D> {

   D toDTO(E entity);

   E toEntity(D dto);

   List<D> toDTO(List<E> entities);

   List<D> toDTO(Iterable<E> entities);

   List<E> toEntity(List<D> dtos);

   void fromDTO(D dto, E entity);

}
