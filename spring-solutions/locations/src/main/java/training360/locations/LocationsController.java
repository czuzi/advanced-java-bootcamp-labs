package training360.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationsController {

    @GetMapping("/locations")
    public String getFavouritePlaces() {
        return "These are my favourite places";
    }
}
