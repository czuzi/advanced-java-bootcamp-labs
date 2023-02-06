package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

	LocationParser locationParser;
	@Test
	void testParse() {
		locationParser = new LocationParser();
		Location location = locationParser.parse("Budapest,47.497912,19.040235");
		assertEquals("Budapest", location.getName());
		assertEquals(47.497912, location.getLatitude());
	}
}