package training360.nav.controller.mappers;

import org.mapstruct.Mapper;
import training360.nav.dtos.NavTypeDto;
import training360.nav.model.NavType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NavTypeMapper {

    NavTypeDto toDto(NavType type);
    List<NavTypeDto> toDto(List<NavType> types);
}
