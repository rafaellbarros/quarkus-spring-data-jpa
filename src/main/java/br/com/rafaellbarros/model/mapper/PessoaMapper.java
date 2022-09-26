package br.com.rafaellbarros.model.mapper;

import br.com.rafaellbarros.core.model.mapper.BaseMapper;
import br.com.rafaellbarros.model.dto.PessoaDTO;
import br.com.rafaellbarros.model.entity.PessoaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 26/09/2022
 */

@Mapper(componentModel = "cdi")
public interface PessoaMapper extends BaseMapper<PessoaEntity, PessoaDTO> {

    @Override
    PessoaDTO toDTO(PessoaEntity entity);

    @Override
    PessoaEntity toEntity(PessoaDTO dto);

    @Override
    List<PessoaDTO> toDTO(List<PessoaEntity> entities);

    @Override
    List<PessoaEntity> toEntity(List<PessoaDTO> dtos);

    @Override
    @InheritInverseConfiguration(name = "toDTO")
    void fromDTO(PessoaDTO dto, @MappingTarget PessoaEntity entity);

}
