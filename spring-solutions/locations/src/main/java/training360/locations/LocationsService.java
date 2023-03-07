package training360.locations;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = List.of(
            new Location("Budapest", 22.2312, 23.231),
            new Location("New York", 22.2312, 23.231),
            new Location("London", 22.2312, 23.231)
    );

    public List<Location> getLocations() {
        return locations;
    }
}
