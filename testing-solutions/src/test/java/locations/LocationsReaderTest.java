package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;


class LocationsReaderTest {

	LocationsReader locationsReader;

	@BeforeEach
	void init() {
		locationsReader = new LocationsReader();
	}

	@Test
	void testLocationReader() {
		List<Location> locations = locationsReader.readLocations("src/test/resources/locationstoread.csv");
		assertThat(locations)
				.hasSize(5).extracting(Location::getName).contains("Y");
	}

	@Test
	void testFilteredLocations() {
		List<Location> locations = locationsReader.readLocations("src/test/resources/locations.csv");
		List<Location> filteredLocations = locationsReader.filterLocationsBeyondArcticCircle(locations);

		assertThat(filteredLocations)
				.extracting(Location::getName)
				.hasSize(2)
				.contains("A")
				.doesNotContain("X")
				.containsOnly("A", "B");

		assertThat(filteredLocations)
				.filteredOn(location -> location.getLatitude() == location.getLongitude())
				.extracting(Location::getName, Location::getLatitude)
				.contains(tuple("A", 70.0));
	}
}
