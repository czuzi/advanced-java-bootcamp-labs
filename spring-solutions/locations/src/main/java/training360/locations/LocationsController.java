package training360.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping("/locations")
    public String getLocations() {
        List<Location> locations = service.getLocations();
        StringBuilder sb = new StringBuilder();
        for (Location l: locations) {
            sb.append("<p>").append(l.toString()).append("</p>");
        }
        return sb.toString();
    }
}
