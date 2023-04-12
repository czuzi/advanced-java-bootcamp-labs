package training360.locations.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import training360.locations.controllers.repositories.LocationRepository;
import training360.locations.dtos.CreateLocationCommand;
import training360.locations.dtos.LocationDto;
import training360.locations.dtos.LocationMapper;
import training360.locations.dtos.UpdateLocationCommand;
import training360.locations.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LocationsService {

    private AtomicLong id = new AtomicLong(0);
    private LocationMapper locationMapper;
    private LocationRepository locationRepository;

    public List<LocationDto> getLocations(Optional<String> term) {
        List<Location> locations = new ArrayList<>();
        if (term.isEmpty()) {
            locations.addAll(locationRepository.findAll());
        } else {
            locations.addAll(locationRepository.findAllByNameContainsIgnoreCase(term.get()));
        }
        return locationMapper.toDto(locations);
    }

    public LocationDto findLocationById(long id) {
        return locationMapper.toDto(locationRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(
                id.getAndIncrement(),
                command.getName(),
                command.getLat(),
                command.getLon()
        );
        locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    public LocationDto updateLocationById(Long id, UpdateLocationCommand command) {
        Location location = locationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return locationMapper.toDto(location);
    }

    public void deleteLocationById(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        locationRepository.delete(location);
    }
}
