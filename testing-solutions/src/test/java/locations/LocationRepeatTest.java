package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationRepeatTest {

	double[][] coordinates = {{0, 50, 1}, {23, 43, 0}, {0, 32, 1}, {56, 56, 0}};

	@RepeatedTest(value = 4, name = "{currentRepetition} / {totalRepetitions}")
	void testEquator(RepetitionInfo info) {
		int i = info.getCurrentRepetition() - 1;
		Location location = new Location("X", coordinates[i][0], coordinates[i][1]);

		assertEquals(coordinates[i][2] == 1, location.isOnEquator());
	}

	@ParameterizedTest(name = "Latitude = {0}, Longitude = {1}, Expected = {2}")
	@MethodSource("getLocations")
	void testPrimeMeridian(int latitude, int longitude, boolean expected) {
		Location location = new Location("X", latitude, longitude);

		assertEquals(expected, location.isOnPrimeMeridian());
	}

	@ParameterizedTest(name = "Latitude = {0}, Longitude = {1}, Expected = {2}")
	@CsvFileSource(resources = "/locations.csv")
	void testPrimeMeridianFromFile(int latitude, int longitude, boolean expected) {
		Location location = new Location("X", latitude, longitude);

		assertEquals(expected, location.isOnPrimeMeridian());
	}

	static Stream<Arguments> getLocations() {
		return Stream.of(
				Arguments.arguments(50, 0, true),
				Arguments.arguments(23, 43, false),
				Arguments.arguments(32, 0, true),
				Arguments.arguments(56, 56, false)
		);
	}

}
