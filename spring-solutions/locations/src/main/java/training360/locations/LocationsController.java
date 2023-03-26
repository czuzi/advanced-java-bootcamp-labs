package training360.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String getLocations(@RequestParam Optional<String> term) {
        List<LocationDto> locations = service.getLocations(term);
        StringBuilder sb = new StringBuilder();
        for (LocationDto l: locations) {
            sb.append("<p>").append(l.toString()).append("</p>");
        }
        return sb.toString();
    }
}
