package training360.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {

    private ModelMapper mapper;
    private AtomicLong id = new AtomicLong(0);
    private List<Location> locations = new ArrayList<>(List.of(
            new Location(id.getAndIncrement(), "Budapest", 22.2312, 23.231),
            new Location(id.getAndIncrement(), "New York", 22.2312, 23.231),
            new Location(id.getAndIncrement(), "London", 22.2312, 23.231)
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

    public LocationDto findLocationById(long id) {
        return mapper.map(locations.stream()
                .filter(location -> location.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new),
                LocationDto.class);

    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(
                id.getAndIncrement(),
                command.getName(),
                command.getLat(),
                command.getLon()
        );
        locations.add(location);
        return mapper.map(location, LocationDto.class);
    }
}
