package training360.locations.controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.locations.model.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByNameContainsIgnoreCase(String term);
}
