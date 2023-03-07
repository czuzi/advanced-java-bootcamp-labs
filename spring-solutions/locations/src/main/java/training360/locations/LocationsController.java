package training360.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    @GetMapping("/locations")
    public String getLocations() {
        Location budapest = new Location("Budapest", 22.2312, 23.231);
        Location newYork = new Location("New York", 22.2312, 23.231);
        Location london = new Location("London", 22.2312, 23.231);
        List<Location> locations = List.of(budapest, newYork, london);
        StringBuilder sb = new StringBuilder();
        for (Location l: locations) {
            sb.append("<p>").append(l.toString()).append("</p>");
        }
        return sb.toString();
    }
}
