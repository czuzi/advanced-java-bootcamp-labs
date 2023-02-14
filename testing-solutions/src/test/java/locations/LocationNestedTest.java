package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {

	LocationParser locationParser;

	@BeforeEach
	void init() {
		locationParser = new LocationParser();
	}

	@Nested
	class FirstFavouriteLocation {

		Location favouriteLocation;

		@BeforeEach
		void setUp() {
			favouriteLocation = locationParser.parse("Middle,0.0,0.0");
		}

		@Test
		void testIsOnEquator() {
			assertTrue(favouriteLocation.isOnEquator());
		}

		@Test
		void testIsOnPrimeMeridian() {
			assertTrue(favouriteLocation.isOnPrimeMeridian());
		}
	}
	@Nested
	class SecondFavouriteLocation {

		Location favouriteLocation;

		@BeforeEach
		void setUp() {
			favouriteLocation = locationParser.parse("Budapest,47.497912,19.040235");
		}

		@Test
		void testIsOnEquatorNotOnEquator() {
			assertFalse(favouriteLocation.isOnEquator());
		}

		@Test
		void testIsOnPrimeMeridianNotOnPrimeMeridian() {
			assertFalse(favouriteLocation.isOnPrimeMeridian());
		}
	}
}
