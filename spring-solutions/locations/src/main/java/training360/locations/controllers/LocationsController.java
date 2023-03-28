package training360.locations.controllers;

import org.springframework.web.bind.annotation.*;
import training360.locations.dtos.UpdateLocationCommand;
import training360.locations.services.LocationsService;
import training360.locations.dtos.CreateLocationCommand;
import training360.locations.dtos.LocationDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<String> term) {
        return service.getLocations(term);
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return service.findLocationById(id);
    }

    @PostMapping
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return service.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocationById(@PathVariable("id") Long id, @RequestBody UpdateLocationCommand command) {
        return service.updateLocationById(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable("id") Long id) {
        service.deleteLocationById(id);
    }
}
