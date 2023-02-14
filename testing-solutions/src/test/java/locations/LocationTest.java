package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Location class")
class LocationTest {

	LocationParser locationParser;
	@BeforeEach
	void init() {
		locationParser = new LocationParser();
	}

	@Test
	void isOnEquator() {
		assertTrue(new Location("Equator", 0, 34.213).isOnEquator());
	}

	@Test
	void isOnPrimeMeridian() {
		assertTrue(new Location("Meridian", 123.1211, 0).isOnPrimeMeridian());
	}

	@Test
	void testDifferentLocationParsers() {
		LocationParser locationParser = new LocationParser();

		assertNotEquals(locationParser, this.locationParser);
		assertNotSame(locationParser, this.locationParser);
	}

	@Test
	void testBudapestDebrecenDistance() {
		Location budapest = locationParser.parse("Budapest,47.497912,19.040235");
		Location debrecen = locationParser.parse("Debrecen,47.52997,21.63916");
		assertEquals(195.2, budapest.distanceFrom(debrecen));

	}

	@Test
	void testDistanceWithSameCoordinates() {
		Location budapest = locationParser.parse("Budapest,47.497912,19.040235");
		Location budapest2 = locationParser.parse("Budapest,47.497912,19.040235");
		assertEquals(0.0, budapest.distanceFrom(budapest2));
	}

	@Test
	void testException() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> new Location("InvalidLocation", -91.21331, 35.312));
		assertEquals("Invalid parameters", iae.getMessage());
	}
}