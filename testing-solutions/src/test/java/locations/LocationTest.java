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
		assertEquals(true, new Location("Equator", 0, 34.213).isOnEquator());
	}

	@Test
	void isOnPrimeMeridian() {
		assertEquals(true, new Location("Meridian", 123.1211, 0).isOnPrimeMeridian());
	}
}