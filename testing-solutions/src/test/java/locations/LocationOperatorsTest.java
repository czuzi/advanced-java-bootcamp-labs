package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationOperatorsTest {

	List<Location> testLocations = new ArrayList<>();
	LocationOperators locationOperators;

	@BeforeEach
	void init() {

		testLocations.add(new Location("northCity", 23.31244, 12.2331));
		testLocations.add(new Location("otherNorthCity", 34.321, 12.2331));
		testLocations.add(new Location("southCity", -11.1241, 12.2331));
	}

	@Test
	void testFilterOnNorth() {
		locationOperators = new LocationOperators();
		assertEquals(2, locationOperators.filterOnNorth(testLocations).size());
	}
}