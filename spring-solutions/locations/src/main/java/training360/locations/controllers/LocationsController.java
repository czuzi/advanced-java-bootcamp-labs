package training360.locations.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LocationDto> findLocationById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.findLocationById(id));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return service.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocationById(@PathVariable("id") Long id, @RequestBody UpdateLocationCommand command) {
        return service.updateLocationById(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationById(@PathVariable("id") Long id) {
        service.deleteLocationById(id);
    }
}
