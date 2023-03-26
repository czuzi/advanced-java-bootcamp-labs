package training360.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationsService {

    private ModelMapper mapper;
    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1L, "Budapest", 22.2312, 23.231),
            new Location(2L, "New York", 22.2312, 23.231),
            new Location(3L, "London", 22.2312, 23.231)
    ));

    public LocationsService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<LocationDto> getLocations(Optional<String> term) {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> term.isEmpty() || location.getName().toLowerCase().contains(term.get().toLowerCase()))
                .toList();
        return mapper.map(filtered, targetListType);
    }
}
