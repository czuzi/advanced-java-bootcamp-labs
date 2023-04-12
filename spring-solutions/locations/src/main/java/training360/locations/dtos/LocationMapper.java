package training360.locations.dtos;

import org.mapstruct.Mapper;
import training360.locations.model.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);
    List<LocationDto> toDto(List<Location> locations);
}
