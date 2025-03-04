package com.carlosalexis99.soccer.domain.dto;

import com.carlosalexis99.soccer.persistence.entities.Division;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DivisionMapper {

    DivisionMapper mapper = Mappers.getMapper(DivisionMapper.class);

    DivisionDTO divisionToDto(Division division);

    Division dtoToDivision(DivisionDTO dto);
}
